package com.example.dsfe;

import com.example.dsfe.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface RedditAPI {

    String BASE_URL = "https://api.film4fun.net/api/mp3/";

    @Headers("Content-Type: application/json")
    @GET("search?pageNo=1&pageSize=10&keyword=mylove")
    Call<Result> getData();


}
