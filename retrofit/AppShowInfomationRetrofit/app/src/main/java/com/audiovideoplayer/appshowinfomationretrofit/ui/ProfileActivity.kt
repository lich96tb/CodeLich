package com.audiovideoplayer.appshowinfomationretrofit.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.audiovideoplayer.appshowinfomationretrofit.ProfileAdapter
import com.audiovideoplayer.appshowinfomationretrofit.R
import com.audiovideoplayer.appshowinfomationretrofit.interfaces.ApiRetrofit
import com.audiovideoplayer.appshowinfomationretrofit.model.DataItem
import com.audiovideoplayer.appshowinfomationretrofit.model.ProfileModel
import com.audiovideoplayer.appshowinfomationretrofit.util.retrofit
import com.audiovideoplayer.appshowinfomationretrofit.util.toast
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var apiService = retrofit.create(ApiRetrofit::class.java)
        var call = apiService.getProfile(2)

        call.enqueue(object : retrofit2.Callback<ProfileModel> {
            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                toast(this@ProfileActivity, "failure")
            }

            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                Log.e("AAAAAAA"," "+response.body()?.total)
                toast(this@ProfileActivity, "ok")
//                recyclerViewPage.apply {
//                    adapter=ProfileAdapter(response.body()?.data as List<DataItem>)
//                    layoutManager=LinearLayoutManager(this@ProfileActivity)
//                    hasFixedSize()
//                }
            }

        })
    }
}
