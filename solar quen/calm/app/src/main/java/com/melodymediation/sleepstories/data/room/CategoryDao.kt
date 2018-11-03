package com.melodymediation.sleepstories.data.room

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE parent_id = :parentId")
    fun getCategoriesByParentId(parentId: String): LiveData<List<Category>>

    @Query("SELECT * FROM category where parent_id = '' ORDER BY level")
    fun getCategoriesParent(): List<Category>

    @Query("SELECT * FROM category")
    fun allCategories(): DataSource.Factory<Int, Category>

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryById(categoryId: String): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<Category>)
}
