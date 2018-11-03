package com.melodymediation.sleepstories.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface VersionDao {

    @Query("SELECT * FROM version")
    fun getVersionsList(): List<Version>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(version: List<Version>)
}
