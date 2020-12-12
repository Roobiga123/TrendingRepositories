package com.example.repo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.repo.model.DataModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public
interface DataDao {
    @Insert(onConflict = REPLACE)
    void insert(DataModel dataModel);

    @Query("select * from  datatable  ORDER BY id ASC")
  LiveData<List<DataModel>> getAllData();
    @Query("delete from datatable")
    void deleteDataAll();
    @Insert(onConflict = REPLACE)
    void  insert(List<DataModel>dataDaoList);
}
