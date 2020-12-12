package com.example.repo.network;

import com.example.repo.model.DataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("repos?indent=4")
    Call<DataWrapper> getDataList();
}
