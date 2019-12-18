package com.example.navigationandroidbase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }


    override fun onBackPressed() {
        Log.e("autolog", "onBackPressed: ");
        super.onBackPressed()
    }
}
