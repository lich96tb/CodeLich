package com.melodymediation.sleepstories.data.remote
import com.melodymediation.sleepstories.data.room.Category
import com.google.gson.annotations.SerializedName
data class CategoryResponse (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("parentId") var parentId: String

)
fun CategoryResponse.toCategoryEntity() =
    this.run {
        if (parentId == null) {
            parentId = ""
        }
        if (icon == null) {
            icon = ""
        }
        Category(id, name, icon, icon, parentId, 0)
    }
