package com.mindorks.framework.mvp.data.network.model.login

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

data class LoginResquest(
        @field:SerializedName("group")
        var group: String? = "L",
        @field:SerializedName("user")
        var user: String? = "null",
        @field:SerializedName("session")
        var session: String? = "null",
        @field:SerializedName("data")
        var data: Data? = Data()


) {
    data class Data(


            @field:SerializedName("cmd")
            var cmd: String? = "Web.sCheckLogin",
            @field:SerializedName("p1")
            var p1: String? = "",
            @field:SerializedName("type")
            var type: String? = "string",

            @field:SerializedName("p2")
            var p2: String? = "",

            @field:SerializedName("p3")
            var p3: String? = "S",

            @field:SerializedName("p4")
            var p4: String? = "127.0.0.1"


    )
}