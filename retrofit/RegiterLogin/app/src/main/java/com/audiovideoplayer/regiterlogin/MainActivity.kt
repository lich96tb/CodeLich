package com.audiovideoplayer.regiterlogin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.audiovideoplayer.regiterlogin.interfaces.RedditAPI
import com.audiovideoplayer.regiterlogin.interfaces.User
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegister.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val redditAPI = retrofit.create(RedditAPI::class.java!!)
            val call = redditAPI.registerAccount(edtName.text.toString().trim(),edtPassWord.text.toString().trim(),edtEmail.text.toString().trim())
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.e("ABSD ", "" + response.body()!!.toString())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("ABSD ", "" + t.message.toString())
                }
            })
        }
    }
}
