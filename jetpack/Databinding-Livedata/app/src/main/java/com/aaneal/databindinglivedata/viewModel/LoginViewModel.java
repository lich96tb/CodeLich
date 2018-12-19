package com.aaneal.databindinglivedata.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.aaneal.databindinglivedata.model.LoginModel;
import com.aaneal.databindinglivedata.remote.LoginRepository;

public class LoginViewModel extends ViewModel {

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public MutableLiveData<LoginModel> response;

    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
        response = loginRepository.getData(email.get(), password.get());
    }

    public void login() {
        response = loginRepository.getData(email.get(), password.get());
    }

    public MutableLiveData<LoginModel> getResponse() {
        return response;
    }
}
