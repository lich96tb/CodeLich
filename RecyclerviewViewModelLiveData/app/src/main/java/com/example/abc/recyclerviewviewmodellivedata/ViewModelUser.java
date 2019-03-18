package com.example.abc.recyclerviewviewmodellivedata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.abc.recyclerviewviewmodellivedata.database.AppDatabase;
import com.example.abc.recyclerviewviewmodellivedata.database.User;
import com.example.abc.recyclerviewviewmodellivedata.database.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModelUser extends AndroidViewModel {
    private MutableLiveData<List<User>> userMutableLiveData = new MutableLiveData<>();
    //    private UserDao userDao;
//private ExecutorService executorService;

    public ViewModelUser(@NonNull Application application) {
        super(application);
        // userDao = AppDatabase.getAppDatabase(application).userDao();
       // executorService = Executors.newSingleThreadExecutor();
    }

//    LiveData<List<User>> getAllUser() {
//        return userDao.getListUse();
//    }

    public void setData(List<User> list){
        userMutableLiveData.setValue(list);

    }

    public MutableLiveData<List<User>> getUserMutableLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }
}
