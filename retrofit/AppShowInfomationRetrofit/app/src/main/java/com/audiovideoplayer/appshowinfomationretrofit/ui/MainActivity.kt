package com.audiovideoplayer.appshowinfomationretrofit.ui

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.audiovideoplayer.appshowinfomationretrofit.R
import com.audiovideoplayer.appshowinfomationretrofit.interfaces.ApiRetrofit
import com.audiovideoplayer.appshowinfomationretrofit.util.retrofit
import com.audiovideoplayer.appshowinfomationretrofit.util.toast
import com.audiovideoplayer.retrofitregisterlogin.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegister.setOnClickListener { registerUser() }
        txtProfile.setOnClickListener { startActivity(Intent(this,ProfileActivity::class.java)) }

    }

    private fun registerUser() {
        var dialog = ProgressDialog(this)
        dialog.setTitle("Loading...")
        dialog.show()
        val redditAPI = retrofit.create(ApiRetrofit::class.java!!)
        val call = redditAPI.savePost(
            edtName.text.toString().trim(),
            edtJob.text.toString().trim()
        )

        call.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("AAAAAAAAAAAA", " failure " + t.message.toString())
                dialog.dismiss()
                toast(this@MainActivity,"Error")
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.e("AAAAAAAAAAAA", " ok" + response.body()?.name.toString())
                dialog.dismiss()
            }

        })

    }
}
