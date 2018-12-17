package com.example.admin.retrofitapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DatatClient {

    @FormUrlEncoded
    @POST("posts/2{id}")
    Call<String> InsertData(@Field("id") String id);

    @PUT("posts/{id}")
    @FormUrlEncoded
    Call<String> getData(@Field("id") String id);
}
