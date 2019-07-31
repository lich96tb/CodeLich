package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_app_bar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment,FomatNumber()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment,PathViewSVG()).commit()
      //  supportFragmentManager.beginTransaction().replace(R.id.mainFragment,FomatNumber()).commit()


    }
}
