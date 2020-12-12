package com.example.repo.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.repo.Dao.DataDao;
import com.example.repo.Database.RepositoryDatabase;
import com.example.repo.model.DataModel;

import java.util.List;

public class DataTableRepository {
    private RepositoryDatabase repositoryDatabase;
    private LiveData<List<DataModel>> allData;

private DataDao dataDao;
    public DataTableRepository(Application application) {
        repositoryDatabase = RepositoryDatabase.getINSTANCE(application);
        dataDao = repositoryDatabase.dataDao();
        allData = dataDao.getAllData();}
        public void insert(List<DataModel>dataDaoList) {
        new InsertAsynTask (dataDao).execute(dataDaoList);
        }

    public LiveData<List<DataModel>> getDAllData() {
        return allData;
    }
   static class InsertAsynTask  extends AsyncTask<List<DataModel>, Void, Void> {
        private  DataDao dataDaos;

        public InsertAsynTask(DataDao dataDao) {
          dataDaos = dataDao;
        }

        @Override
        protected Void doInBackground(final List<DataModel>... params) {
    dataDaos.insert(params[0]);

            return null;
        }
    }
}

