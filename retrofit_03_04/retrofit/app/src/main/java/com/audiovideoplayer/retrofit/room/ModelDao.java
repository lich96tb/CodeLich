package com.audiovideoplayer.retrofit.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.audiovideoplayer.retrofit.model.ApiMusic;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ModelDao {
    @Insert(onConflict = REPLACE)
     void insertAll(List<ApiMusic> listResponse);
    @Insert(onConflict = REPLACE)
    void insert(ApiMusic... apiMusic);
    @Query("SELECT * FROM apimusic")
    List<ApiMusic> getAll();
}
