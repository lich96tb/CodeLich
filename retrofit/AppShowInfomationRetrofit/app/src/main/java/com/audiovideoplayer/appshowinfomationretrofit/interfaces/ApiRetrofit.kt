package com.audiovideoplayer.appshowinfomationretrofit.interfaces

import com.audiovideoplayer.appshowinfomationretrofit.model.DataItem
import com.audiovideoplayer.appshowinfomationretrofit.model.ProfileModel
import com.audiovideoplayer.appshowinfomationretrofit.model.UserTest
import com.audiovideoplayer.retrofitregisterlogin.model.Post
import retrofit2.Call
import retrofit2.http.*

interface ApiRetrofit {
    @POST("api/users")
    @FormUrlEncoded
    fun savePost(
            @Field("name") title: String,
            @Field("job") job: String
    ): Call<Post>

    @GET("/api/users")
    fun getProfile(@Query("page") page: Int): Call<ProfileModel>
    //query de noi chuoi
    //chuyen vao thif dungf fe
    //https://square.github.io/retrofit/

    @GET("/api/users/{id}")
    fun getUser(@Path("id") id:Int):Call<UserTest>
}
