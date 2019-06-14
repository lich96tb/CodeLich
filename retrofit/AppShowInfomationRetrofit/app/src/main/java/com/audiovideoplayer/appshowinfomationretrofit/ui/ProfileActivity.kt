package com.audiovideoplayer.appshowinfomationretrofit.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.audiovideoplayer.appshowinfomationretrofit.adapter.ProfileAdapter
import com.audiovideoplayer.appshowinfomationretrofit.R
import com.audiovideoplayer.appshowinfomationretrofit.interfaces.ApiRetrofit
import com.audiovideoplayer.appshowinfomationretrofit.model.DataItem
import com.audiovideoplayer.appshowinfomationretrofit.model.ProfileModel
import com.audiovideoplayer.appshowinfomationretrofit.util.loge
import com.audiovideoplayer.appshowinfomationretrofit.util.retrofit
import com.audiovideoplayer.appshowinfomationretrofit.util.toast
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val apiService = retrofit.create(ApiRetrofit::class.java)
        val call = apiService.getProfile(2)

        call.enqueue(object : retrofit2.Callback<ProfileModel> {
            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                toast(this@ProfileActivity, "failure")
            }

            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                loge(response.body()?.toString()!!)
                toast(this@ProfileActivity, "ok")
                recyclerViewPage.apply {
                    adapter=
                        ProfileAdapter(response.body()?.data as List<DataItem>)
                    layoutManager=LinearLayoutManager(this@ProfileActivity)
                    hasFixedSize()
                }
            }

        })
    }
}
