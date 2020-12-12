package com.example.repo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.repo.Repository.DataRepository;
import com.example.repo.model.DataModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private DataRepository dataRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }
    public LiveData<List<DataModel>> getAllData() {
        return dataRepository.getMutableLiveData();
    }
}