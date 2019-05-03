package com.audiovideoplayer.retrofitbbbbbb.model;


import retrofit2.Call;
import retrofit2.http.GET;


public interface RedditAPI {
    @GET("unknown")
    Call<Response> getData();


}
