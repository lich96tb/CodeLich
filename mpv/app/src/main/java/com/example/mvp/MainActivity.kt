package com.example.mvp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mvp.interfaces.IMainViewApp
import com.example.mvp.prisenter.CustomDialog
import com.example.mvp.prisenter.MainPrisenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainViewApp {
private lateinit var dialog:Dialog
    override fun getListSuccess(listData: ArrayList<String>) {
        Log.e("kkddkd ", " " + listData.size)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog=Dialog(this)
        MainPrisenter(this)
        onClick()

    }

    private fun onClick() {
        imgEdit.setOnClickListener { CustomDialog(dialog, this) }
    }
}
