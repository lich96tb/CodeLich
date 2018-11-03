package com.melodymediation.sleepstories.data.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface SessionDao {
    @Query("SELECT * FROM session")
    fun getSessions(): LiveData<List<Session>>

    @Query("SELECT * FROM session WHERE category_id = :categoryId")
    fun getSessionsByCategory(categoryId: String): LiveData<List<Session>>

    @Query("SELECT * FROM session WHERE type = :type")
    fun getSessionsByType(type: String): List<Session>

    @Query("SELECT * FROM session WHERE category_id = :categoryId LIMIT :pageSize")
    fun getSessionsByCategoryLimit(categoryId: String, pageSize: Int): LiveData<List<Session>>

    @Query("SELECT * FROM session WHERE parent_id = :parentId")
    fun getSessionsByParentId(parentId: String): LiveData<List<Session>>

    @Query("SELECT * FROM session WHERE id = :sessionId")
    fun getSessionById(sessionId: String): Session

    @Query("UPDATE session SET url_dist=:urldist WHERE id=:sessionId")
    fun updateSessionUrlDist(sessionId:String ,urldist:String)
    @Query("UPDATE session SET url_background_dist=:url_background_dist WHERE id=:sessionId")
    fun updateSessionUrlBackground(sessionId:String ,url_background_dist:String)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sessions: List<Session>)


}
