package com.audiovideoplayer.appshowinfomationretrofit.model

data class ProfileModel(
	val perPage: Int? = null,
	val total: Int? = null,
	val data: List<DataItem?>? = null,
	val page: Int? = null,
	val totalPages: Int? = null
)
