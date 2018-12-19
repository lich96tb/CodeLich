package com.aaneal.databindinglivedata.remote;

import com.aaneal.databindinglivedata.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {

    @POST("/api/login")
    Call<LoginModel> userLogin(@Query("email") String email,@Query("password") String password);
}
