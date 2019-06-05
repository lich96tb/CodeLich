package com.audiovideoplayer.appshowinfomationretrofit.interfaces;

import com.audiovideoplayer.appshowinfomationretrofit.model.ProfileModel;
import com.audiovideoplayer.retrofitregisterlogin.model.Post;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIServices {

    @POST("api/users")
    @FormUrlEncoded
    Call<Post> savePost(@Field("name") String title,
                        @Field("job") String job);

    @GET("users")
    Call<ProfileModel> getProfile(@Field("page") String page);
}