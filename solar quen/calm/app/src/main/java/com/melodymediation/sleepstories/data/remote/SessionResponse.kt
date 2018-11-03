package com.melodymediation.sleepstories.data.remote
import com.melodymediation.sleepstories.data.room.Session
import com.google.gson.annotations.SerializedName

data class SessionResponse (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") var url: String,
    @SerializedName("categoryId") var categoryId: String,
    @SerializedName("parentId") var parentId: String,
    @SerializedName("duration") var duration: String,
    @SerializedName("count") var count: String,
    @SerializedName("isFee") val isFee: Boolean,
    @SerializedName("isBuy") val isBuy: Boolean,
    @SerializedName("order") var order: Int,
    @SerializedName("type") var type: String,
    @SerializedName("typeMedia") var typeMedia: String,
    @SerializedName("view") val view: Long,
    @SerializedName("urlBackground") var urlBackground: String,
    @SerializedName("typeBackground") var typeBackground: String
)
fun SessionResponse.toSessionEntity() =
    this.run {
        if (order == null) {
            order = 0
        }
        if (url == null) {
            url = ""
        }
        if (parentId == null) {
            parentId = ""
        }
        if (categoryId == null) {
            categoryId = ""
        }
        if (duration == null) {
            duration = ""
        }
        if (count == null) {
            count = ""
        }
        if (type == null) {
            type = ""
        }
        if (typeMedia == null) {
            typeMedia = ""
        }
        if (urlBackground == null) {
            urlBackground = ""
        }
        if (typeBackground == null) {
            typeBackground = ""
        }
        Session(id, name, url, categoryId,"", duration, count, isFee, isBuy, parentId, order, type, typeMedia, urlBackground,"", typeBackground)
    }
