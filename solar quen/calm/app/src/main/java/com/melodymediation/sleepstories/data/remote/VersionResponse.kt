package com.melodymediation.sleepstories.data.remote

import com.melodymediation.sleepstories.data.room.Version
import com.google.gson.annotations.SerializedName

data class VersionResponse (
    @SerializedName("id") val id: String,
    @SerializedName("name") var name: Int,
    @SerializedName("description") var description: String
)
fun VersionResponse.toVersionEntity() =
    this.run {
        if (name == null) {
            name = 0
        }
        if (description == null) {
            description = ""
        }
        Version(id, name, description)
    }
