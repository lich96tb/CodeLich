package com.audiovideoplayer.appshowinfomationretrofit.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val BASE_URL = "https://reqres.in/"
val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
fun toast(context: Context,content:String){
        Toast.makeText(context,content,Toast.LENGTH_LONG).show()
}
fun loge(content: String){
        Log.e("AAAAAAAA",content)
}