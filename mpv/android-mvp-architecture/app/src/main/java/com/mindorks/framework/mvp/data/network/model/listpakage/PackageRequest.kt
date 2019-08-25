package com.mindorks.framework.mvp.data.network.model.listpakage

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class PackageRequest(

        @field:SerializedName("data")
        val data: Data? = Data(),

        @field:SerializedName("session")
        var session: String? = null,

        @field:SerializedName("user")
        var user: String? = null,

        @field:SerializedName("group")
        val group: String? = "B"
) {
    data class Data(

            @field:SerializedName("p1")
            var p1: String? = null,

            @field:SerializedName("p2")
            val p2: String? = "",

            @field:SerializedName("p3")
            val p3: String? = "",

            @field:SerializedName("p4")
            val p4: String? = "",

            @field:SerializedName("p5")
            var p5: String? = "",

            @field:SerializedName("p6")
            val p6: String? = "1",

            @field:SerializedName("p7")
            val p7: String? = "20",

            @field:SerializedName("p8")
            val p8: String? = "",

            @field:SerializedName("cmd")
            val cmd: String? = "CARD_GetAllCardBalance",

            @field:SerializedName("type")
            val type: String? = "cursor"
    )
}