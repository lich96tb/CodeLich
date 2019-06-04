package com.audiovideoplayer.regiterlogin.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.audiovideoplayer.regiterlogin.ConstantKt.URL_REGISTER;


public interface RedditAPI {
    @FormUrlEncoded
    @POST(URL_REGISTER)
    Call<ResponseBody> registerAccount(@Field("username") String username,
                                       @Field("password") String password,
                                       @Field("email") String email);
}
