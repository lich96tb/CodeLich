package com.example.rxjavaretrofit.network

import retrofit2.http.GET
import com.example.rxjavaretrofit.model.Android
import io.reactivex.Observable


interface RequestInterface {
    @GET("android/jsonarray/")
    fun register(): Observable<List<Android>>
}