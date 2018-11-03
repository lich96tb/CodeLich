package com.melodymediation.sleepstories.data.remote

import com.google.gson.annotations.SerializedName

data class PaginationPropertySort(
    @SerializedName("direction") val direction: String,
    @SerializedName("property") val property: String,
    @SerializedName("ignoreCase") val ignoreCase: Boolean,
    @SerializedName("nullHandling") val nullHandling: String,
    @SerializedName("ascending") val ascending: String,
    @SerializedName("descending") val descending: String
)
