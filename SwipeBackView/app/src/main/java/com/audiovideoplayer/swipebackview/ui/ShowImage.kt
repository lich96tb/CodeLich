package com.audiovideoplayer.swipebackview.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.audiovideoplayer.swipebackview.R
import com.robgas.backswipelayout.BackSwipeLayout
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        var avata = intent.getIntExtra("itemView", R.drawable.abc_btn_default_mtrl_shape)
        imgAvataRun.setImageResource(avata)
        backSwipeLayout.dragDirection = BackSwipeLayout.DragDirections.TOP


    }
}
