package com.audiovideoplayer.retrofit.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RedditApi {
    @GET("musics")
    Call<List<ApiMusic>> getData();
}
