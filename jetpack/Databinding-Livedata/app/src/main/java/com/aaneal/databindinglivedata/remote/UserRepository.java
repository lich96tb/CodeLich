package com.aaneal.databindinglivedata.remote;

import android.arch.lifecycle.MutableLiveData;

public class UserRepository {

    private  MutableLiveData<String> data = new MutableLiveData<>();

    public UserRepository(){
    }

    public void loginUser(String user){
        if(user.equals("anil")){
            data.setValue("Success");
        }else {
            data.setValue("Failed");
        }
    }

    public MutableLiveData<String> getResponse(){
        return data;
    }
}
