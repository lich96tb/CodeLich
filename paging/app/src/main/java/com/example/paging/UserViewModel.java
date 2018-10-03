package com.example.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.example.paging.database.User;
import com.example.paging.database.UserDao;

public class UserViewModel extends ViewModel {
    public LiveData<PagedList<User>> userList;

    public UserViewModel() {

    }

    public void init(UserDao userDao) {
        userList = userDao.usersByFirstName().create(0,
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setPageSize(50)
                        .setPrefetchDistance(50)
                        .build());
    }
}
