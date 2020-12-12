package com.example.repo.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.repo.Dao.DataDao;
import com.example.repo.model.DataModel;

@Database(entities = {DataModel.class}, version = 1)
public abstract class RepositoryDatabase  extends RoomDatabase {
    private static final String DATABASE_NAME ="RepositoryDatabase";
    public abstract DataDao dataDao();


    private static  volatile RepositoryDatabase INSTANCE;

    public static RepositoryDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (RepositoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, RepositoryDatabase.class, "DATABASE").addCallback(callback).build();

                }
            }
        }
        return INSTANCE;
    }
    @Override
    public void close() {
        super.close();
        INSTANCE = null;
    }


static Callback callback = new Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onOpen(db);
        new PopulateAsyncTask(INSTANCE);
    }
};
    static  class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private   final  DataDao dataDao;
        public PopulateAsyncTask(RepositoryDatabase repositoryDatabase) {
            dataDao = repositoryDatabase.dataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataDao.deleteDataAll();
            return null;
        }
    }
}
