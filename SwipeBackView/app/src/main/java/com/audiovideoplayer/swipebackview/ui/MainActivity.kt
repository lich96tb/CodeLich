package com.audiovideoplayer.swipebackview.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.audiovideoplayer.swipebackview.AdapterItemAvata
import com.audiovideoplayer.swipebackview.R
import com.audiovideoplayer.swipebackview.interfaces.ClickItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ClickItem {
    override fun clickItemImage(itmPosition: Int) {
        imgAvata.setImageResource(itmPosition)
        item = itmPosition
    }

    private lateinit var list: ArrayList<Int>
    private var item: Int = 0
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.audiovideoplayer.swipebackview.R.layout.activity_main)
        addData()
        item=list[0]
        recyclervieeItem.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false) as RecyclerView.LayoutManager?
            adapter = list.let {
                AdapterItemAvata(list, this@MainActivity)
            }

            hasFixedSize()
        }
        imgAvata.setOnClickListener {
            var intent = Intent(this, ShowImage::class.java)
            intent.putExtra("itemView", item)
            startActivity(intent)
        }
        btnNectFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.mainActivity, Test()).addToBackStack(null).commit()
        }

    }

    private fun addData() {
        list = ArrayList<Int>()
        list.add(R.drawable.nhuna)
        list.add(R.drawable.nhung2)
        list.add(R.drawable.girl3)
        list.add(R.drawable.girl4)

        list.add(R.drawable.nhuna)
        list.add(R.drawable.nhung2)
        list.add(R.drawable.girl3)
        list.add(R.drawable.girl4)

        list.add(R.drawable.nhuna)
        list.add(R.drawable.nhung2)
        list.add(R.drawable.girl3)
        list.add(R.drawable.girl4)

        list.add(R.drawable.nhuna)
        list.add(R.drawable.nhung2)
        list.add(R.drawable.girl3)
        list.add(R.drawable.girl4)


    }
}
