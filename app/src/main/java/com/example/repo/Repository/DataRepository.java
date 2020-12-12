package com.example.repo.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.repo.model.DataModel;
import com.example.repo.model.DataWrapper;
import com.example.repo.network.RestApiService;
import com.example.repo.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private ArrayList<DataModel> data_details = new ArrayList<>();
    private MutableLiveData<List<DataModel>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    public DataRepository(Application application) {
        this.application = application;
    }
    public MutableLiveData<List<DataModel>> getMutableLiveData() {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<DataWrapper> call = apiService.getDataList();
        call.enqueue(new Callback<DataWrapper>() {
            @Override
            public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {
                DataWrapper dataWrapper = response.body();
                if (dataWrapper != null && dataWrapper.getmData() != null) {
                    data_details = (ArrayList<DataModel>) dataWrapper.getmData();
                    mutableLiveData.setValue(data_details);
                }

            }

            @Override
            public void onFailure(Call<DataWrapper> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());

            }



        });
        return mutableLiveData;
    }
}
