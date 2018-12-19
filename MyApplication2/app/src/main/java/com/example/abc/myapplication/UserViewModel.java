package com.example.abc.myapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class UserViewModel extends ViewModel {
    private ObservableField<String> name = new ObservableField<>();
    private ObservableField<String> email = new ObservableField<>();
    private MutableLiveData<String> soa = new MutableLiveData<>();


    public MutableLiveData<String> getSoa() {
        return soa;
    }

    public void login() {
        soa.setValue(name.get() + email.get());

    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public void setEmail(ObservableField<String> email) {
        this.email = email;
    }
}
