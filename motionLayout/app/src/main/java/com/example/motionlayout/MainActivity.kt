package com.example.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.motionlayout.home.Home

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.mainActivity, Home()).commit()
    }
}
