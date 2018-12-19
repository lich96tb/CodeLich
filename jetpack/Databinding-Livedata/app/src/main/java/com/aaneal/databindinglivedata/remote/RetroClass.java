package com.aaneal.databindinglivedata.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    private static final String BASE_URL ="http://wfp.robustitconcepts.com/";

    private static Retrofit getRetroInstance(){

        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiClient getApiService(){

        return getRetroInstance().create(ApiClient.class);

    }

}
