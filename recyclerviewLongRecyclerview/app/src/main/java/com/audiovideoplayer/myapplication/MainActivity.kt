package com.audiovideoplayer.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.audiovideoplayer.myapplication.dapter.ParentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_parent.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ParentAdapter(createParent())
        }

    }
}
