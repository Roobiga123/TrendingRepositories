package com.example.repo.network;

import com.example.repo.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("{url}")
    Call<List<DataModel>> getList(
            @Path("url") String url
    );

    @GET("repos/{id}")
    Call<DataModel> searchData(
            @Path("id") String id
    );

}