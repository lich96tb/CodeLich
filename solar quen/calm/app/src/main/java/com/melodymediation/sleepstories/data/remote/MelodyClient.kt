package com.melodymediation.sleepstories.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun getCategoryService(): CategoryService = createRetrofit().create(CategoryService::class.java)

fun getSessionService(): SessionService = createSessionRetrofit().create(SessionService::class.java)

fun getVersionService(): VersionService = createVersionRetrofit().create(VersionService::class.java)

fun getCategorySimple(): CategoryService = createRetrofitSimple().create(CategoryService::class.java)

private fun createRetrofitSimple(): Retrofit = Retrofit.Builder()
    .baseUrl(Api.MELODY_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(provideOkHttpClient())
    .build()

private fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(Api.MELODY_URL)
    .addConverterFactory(GsonConverterFactory.create(gsonDeserializer()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(provideOkHttpClient())
    .build()

private fun createSessionRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(Api.MELODY_URL)
    .addConverterFactory(GsonConverterFactory.create(gsonSessionDeserializer()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(provideOkHttpClient())
    .build()

private fun createVersionRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(Api.MELODY_URL)
    .addConverterFactory(GsonConverterFactory.create(gsonVersionDeserializer()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(provideOkHttpClient())
    .build()

private fun gsonDeserializer(): Gson = GsonBuilder()
    .registerTypeAdapter(object : TypeToken<List<@JvmSuppressWildcards CategoryResponse>>() {}.type, MelodyDeserializer<CategoryResponse>())
    .setLenient()
    .create()

private fun gsonSessionDeserializer(): Gson = GsonBuilder()
    .registerTypeAdapter(object : TypeToken<List<@JvmSuppressWildcards SessionResponse>>() {}.type, MelodyDeserializer<SessionResponse>())
    .setLenient()
    .create()

private fun gsonVersionDeserializer(): Gson = GsonBuilder()
    .registerTypeAdapter(object : TypeToken<List<@JvmSuppressWildcards VersionResponse>>() {}.type, MelodyDeserializer<VersionResponse>())
    .setLenient()
    .create()

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
