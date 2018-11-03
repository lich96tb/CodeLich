package com.melodymediation.sleepstories.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * urlDist : link object in storage
 * type :  type of object example (song, lesson, background)
 * url_background : link background
 * url_background_dist :link background dist
 */
@Entity(tableName = "session")
data class Session (
        @PrimaryKey @ColumnInfo(name = "id") val sessionId: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "url")
        val url: String,
        @ColumnInfo(name = "category_id")
        val categoryId: String,
        @ColumnInfo(name = "url_dist")
        var urlDist: String,
        @ColumnInfo(name = "duration")
        val duration: String,
        @ColumnInfo(name = "count")
        val count: String,
        @ColumnInfo(name = "is_fee")
        val isFee: Boolean,
        @ColumnInfo(name = "is_buy")
        val isBuy: Boolean,
        @ColumnInfo(name = "parent_id")
        val parentId: String,
        @ColumnInfo(name = "order")
        val order: Int,
        @ColumnInfo(name = "type")
        val type: String,
        @ColumnInfo(name = "type_media")
        val typeMedia: String,
        @ColumnInfo(name = "url_background")
        val urlBackground: String,
        @ColumnInfo(name = "url_background_dist")
        var urlBackgroundDist: String,
        @ColumnInfo(name = "type_background")
        val typeBackground: String
) : Serializable {

    override fun toString() = name
}
