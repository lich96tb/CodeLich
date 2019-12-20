package com.example.abc.viewmodel_databinding_livedata.abc;

import android.annotation.TargetApi;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.abc.viewmodel_databinding_livedata.CheckPassWord;

import java.util.Objects;

public class UserViewModel extends ViewModel {
    //private ObservableField<String> name = new ObservableField<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private ObservableField<String> email = new ObservableField<>();
    private MutableLiveData<CheckPassWord> pass = new MutableLiveData<>();
    //khoi tao
    CheckPassWord checkPassWord = new CheckPassWord();

    //nhan gia tri khi lang nghe
    public MutableLiveData<CheckPassWord> getAllData() {
        return pass;
    }

    //su kien khi an vao nut login
    public void LoginUser() {
        pass.setValue(checkPassWord);
        Log.e("ABDdd", ""+checkPassWord.getEmailCheck()+"  "+checkPassWord.getNameCheck());
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

//    public ObservableField<String> getName() {
//        return name;
//    }
//
//    public void setName(ObservableField<String> name) {
//        this.name = name;
//    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public void setEmail(ObservableField<String> email) {
        this.email = email;
    }


    //lang nghe su kien click editText
    public TextWatcher watcherName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void afterTextChanged(Editable s) {
//            if (!Objects.equals(name.get(), s.toString())) {
//                checkPassWord.setNameCheck(s + "");
//
//            }
        }
    };

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
//            if (!Objects.equals(name.get(), s.toString())) {
//                checkPassWord.setEmailCheck(s + "");
//            }
        }
    };


}
