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
import com.audiovideoplayer.swipebackview.interfaces.ClickItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ClickItem {
    override fun clickItemImage(itmPosition: Int) {
        imgAvata.setImageResource(itmPosition)
        item = itmPosition
        // startActivity(Intent(this, ShowImage::class.java).putExtra("itemView", itmPosition))
    }

    private lateinit var list: ArrayList<Int>
    private var item: Int = 0
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.audiovideoplayer.swipebackview.R.layout.activity_main)
        addData()
        Log.e("ASDFS ", " ${list.size}")
        recyclervieeItem.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
            adapter = list.let {
                AdapterItemAvata(list, this@MainActivity)
            }

            hasFixedSize()
        }
        imgAvata.setOnClickListener {
            Log.e("AAAAAAAA111", "   " + item)
            var intent = Intent(this, ShowImage::class.java)
            intent.putExtra("itemView", item)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

    }

    private fun addData() {
        list = ArrayList<Int>()
        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhuna)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhung2)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl3)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl4)

        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhuna)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhung2)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl3)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl4)

        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhuna)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhung2)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl3)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl4)

        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhuna)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.nhung2)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl3)
        list.add(com.audiovideoplayer.swipebackview.R.drawable.girl4)


    }
}
