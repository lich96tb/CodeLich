package com.aaneal.databindinglivedata.remote;

import android.arch.lifecycle.MutableLiveData;

import com.aaneal.databindinglivedata.model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {

    private MutableLiveData<LoginModel> data = new MutableLiveData<>();

    public LoginRepository() {

    }

    public MutableLiveData<LoginModel> getData(String username, String password) {

        ApiClient apiClient = RetroClass.getApiService();
        Call<LoginModel> call = apiClient.userLogin(username, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
        return data;
    }
}
