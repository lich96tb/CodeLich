package com.audiovideoplayer.swipebackview.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.audiovideoplayer.swipebackview.R
import com.audiovideoplayer.swipebackview.library.ActivityBaseBack
import com.liuguangqiang.swipeback.SwipeBackActivity
import com.liuguangqiang.swipeback.SwipeBackLayout
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImage : ActivityBaseBack() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        var avata = intent.getIntExtra("itemView", R.drawable.abc_btn_default_mtrl_shape)
        imgAvataRun.setImageResource(avata)
        setDragEdge(SwipeBackLayout.DragEdge.LEFT)

    }
}
