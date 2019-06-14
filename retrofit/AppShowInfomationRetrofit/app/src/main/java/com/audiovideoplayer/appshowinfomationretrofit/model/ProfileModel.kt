package com.audiovideoplayer.appshowinfomationretrofit.model

data class ProfileModel(
	val per_page: Int? = null,
	val total: Int? = null,
	val data: List<DataItem?>? = null,
	val page: Int? = null,
	val totalPages: Int? = null
)
