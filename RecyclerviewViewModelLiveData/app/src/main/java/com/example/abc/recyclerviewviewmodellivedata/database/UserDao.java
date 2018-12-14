package com.example.abc.recyclerviewviewmodellivedata.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface UserDao {
    @Query("Select * FROM user")
    List<User> getListUse();

    @Insert
    void insert(User user);

    // xoas theo id
    @Query("DELETE FROM user WHERE id=(:id)")
    void Delete(long id);// xoas theo id

    @Query("SELECT * FROM user WHERE id=(:id)")
    User getDataItem(long id);

}
