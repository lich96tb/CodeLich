package com.example.abc.recyclerviewviewmodellivedata.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * FROM user")
    LiveData<List<User>> getListUse();
    @Query("Select * FROM user")
    List<User> getListData();

    @Insert
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<User> posts);


    // xoas theo id
    @Query("DELETE FROM user WHERE id=(:id)")
    void Delete(long id);// xoas theo id

    @Query("SELECT * FROM user WHERE id=(:id)")
    User getDataItem(long id);


}
