package com.audiovideoplayer.appshowinfomationretrofit.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.audiovideoplayer.appshowinfomationretrofit.R
import com.audiovideoplayer.appshowinfomationretrofit.interfaces.ApiRetrofit
import com.audiovideoplayer.appshowinfomationretrofit.model.DataItem
import com.audiovideoplayer.appshowinfomationretrofit.model.ProfileModel
import com.audiovideoplayer.appshowinfomationretrofit.model.UserTest
import com.audiovideoplayer.appshowinfomationretrofit.util.loge
import com.audiovideoplayer.appshowinfomationretrofit.util.retrofit
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_single_user.*
import kotlinx.android.synthetic.main.item_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_user)
        var id=intent.getIntExtra("id",0)



        val apiService = retrofit.create(ApiRetrofit::class.java)
        val call = apiService.getUser(id)
        call.enqueue(object : Callback<UserTest>{
            override fun onFailure(call: Call<UserTest>, t: Throwable) {
            }

            override fun onResponse(call: Call<UserTest>, response: Response<UserTest>) {
                loge(response.body().toString())
                var user=response.body()?.data
                txtMailUser.text=user?.email
                txtFirstNameUser.text=user?.first_name
                txtLastNameUser.text=user?.last_name
                Glide.with(this@SingleUserActivity).load(user?.avatar).into(imgAvataUser)
            }

        }
        )
    }
}
