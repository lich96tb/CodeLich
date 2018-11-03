package com.melodymediation.sleepstories.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "version")
class Version (
    @PrimaryKey @ColumnInfo(name = "id") val versionId: String,
    @ColumnInfo(name = "name")
    val name: Int,
    @ColumnInfo(name = "description")
    val description: String
) {

    override fun toString() = name.toString()
}
