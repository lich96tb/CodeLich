package com.melodymediation.sleepstories.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "category")
data class Category (
    @PrimaryKey @ColumnInfo(name = "id") val categoryId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "ico")
    val ico: String,
    @ColumnInfo(name = "ico_url")
    val icoUrl: String,
    @ColumnInfo(name = "parent_id")
    val parentId: String,
    @ColumnInfo(name = "level")
    val level: Int


) {

    override fun toString() = name
}
