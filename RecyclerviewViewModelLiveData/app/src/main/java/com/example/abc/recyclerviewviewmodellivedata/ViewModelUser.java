package com.example.abc.recyclerviewviewmodellivedata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.abc.recyclerviewviewmodellivedata.database.User;

import java.util.ArrayList;
import java.util.List;

public class ViewModelUser extends AndroidViewModel {
    private MutableLiveData<List<User>> userMutableLiveData=new MutableLiveData<>();

    public ViewModelUser(@NonNull Application application) {
        super(application);
        List<User> list = new ArrayList<>();
        list.add(new User("lich", "1"));
        list.add(new User("Lan", "2"));
        list.add(new User("Hoa", "3"));
        userMutableLiveData.setValue(list);
    }


    public MutableLiveData<List<User>> getUserMutableLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }
}
