package com.mindorks.framework.mvp.data.network.model.login

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class LoginResponse(

	@field:SerializedName("rc")
	var rc: Int? = null,

	@field:SerializedName("rs")
	var rs: String? = null,

	@field:SerializedName("data")
	var data: Data? = null,

	@field:SerializedName("cmd")
	var cmd: String? = null,

	@field:SerializedName("oID")
	var oID: String? = null
){
	data class Data(

			@field:SerializedName("CountLoginFail")
			var countLoginFail: Int? = null,

			@field:SerializedName("defaultAcc")
			var defaultAcc: String? = null,

			@field:SerializedName("address")
			var address: String? = null,

			@field:SerializedName("AuthenType")
			var authenType: String? = null,

			@field:SerializedName("IP")
			var iP: String? = null,

			@field:SerializedName("name")
			var name: String? = null,

			@field:SerializedName("iFlag")
			var iFlag: Int? = null,

			@field:SerializedName("user")
			var user: String? = null,

			@field:SerializedName("sid")
			var sid: String? = null
	)
}