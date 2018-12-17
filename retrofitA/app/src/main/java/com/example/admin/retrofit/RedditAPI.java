package com.example.admin.retrofit;

import com.example.admin.retrofit.abc.Responsea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface RedditAPI {
    @GET("unknown")
    Call<Responsea> getData();


}
