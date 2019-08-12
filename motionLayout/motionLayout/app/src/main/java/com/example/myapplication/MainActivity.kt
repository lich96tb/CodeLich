package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.ImageFilterView.ImageFilterView
import com.example.myapplication.ImageFilterView.ImageOneFilterView
import com.example.myapplication.doi_vi_tri.DoiV

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      supportFragmentManager.beginTransaction().replace(R.id.fragmentMain,DoiV()).commit()
    //  supportFragmentManager.beginTransaction().replace(R.id.fragmentMain,ImageOneFilterView()).commit()
     // supportFragmentManager.beginTransaction().replace(R.id.fragmentMain,ImageFilterView()).commit()
    }
}
