package com.aaneal.databindinglivedata.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.aaneal.databindinglivedata.remote.UserRepository;

public class NameModel extends ViewModel {

    public MutableLiveData<String> data;

    private UserRepository userRepository;

    public ObservableField<String> nameVal = new ObservableField<>("");

    public NameModel(){
        userRepository = new UserRepository();
        userRepository.loginUser("anil");
        data = userRepository.getResponse();
    }

    public void login(){
        userRepository.loginUser("anil1");
        data = userRepository.getResponse();
    }

    public MutableLiveData<String> getData() {
        return data;
    }
}
