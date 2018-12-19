package com.example.admin.loginapp.jetpack;

import android.annotation.TargetApi;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class ViewModelLogin extends ViewModel {
    private ObservableField<String> name = new ObservableField<>("");
    private ObservableField<String> email = new ObservableField<>("");
//    private MutableLiveData<User> userMutableLiveData;
//
//    public ViewModelLogin() {
//        userMutableLiveData=new MutableLiveData<>();
//    }

    public ObservableField<String> getName() {
        return name;
    }

    public String show() {
        return name.get() + email.get();
    }
public void  setDataUser(User user){
        name.set(user.getName());
        email.set(user.getMail());
}
//    public MutableLiveData<User> getUserMutableLiveData() {
//        return userMutableLiveData;
//    }
    public void Login(){
    //    userMutableLiveData.setValue(new User(name.get(),email.get()));
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


    public TextWatcher watcherPass = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void afterTextChanged(Editable s) {
            if (!Objects.equals(email.get(), s.toString())) {
                email.set(s.toString());
            }
        }
    };

    public TextWatcher watcheName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void afterTextChanged(Editable s) {
            if (!Objects.equals(name.get(), s.toString())) {
                name.set(s.toString());

            }
        }
    };
}
