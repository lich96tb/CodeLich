package com.example.paging.database;

import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<User> users);

    @Query("SELECT * FROM User")
    public abstract LivePagedListProvider<Integer,User> usersByFirstName();
}
