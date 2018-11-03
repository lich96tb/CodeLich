package com.melodymediation.sleepstories.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class  PaginationPage<T> : Serializable {
    @SerializedName("content")
    val content: List<T>? = null

    @SerializedName("last")
    var last: Boolean? = null

    @SerializedName("first")
    var first: Boolean? = null

    @SerializedName("number")
    var number: Integer? = null

    @SerializedName("totalPages")
    var totalPages: Integer? = null


    @SerializedName("itemsPerPage")
    var itemsPerPage: Integer? = null

    @SerializedName("totalElements")
    var totalElements: Integer? = null

    @SerializedName("numberOfElements")
    var numberOfElements: Integer? = null

    @SerializedName("sort")
    var sort: Array<PaginationPropertySort>? = null
}
