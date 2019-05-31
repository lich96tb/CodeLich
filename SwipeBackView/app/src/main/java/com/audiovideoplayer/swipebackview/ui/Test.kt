package com.audiovideoplayer.swipebackview.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.audiovideoplayer.swipebackview.R
import com.robgas.backswipelayout.BackSwipeLayout
import kotlinx.android.synthetic.main.fragment_test.*

class Test : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeBackground.dragDirection=BackSwipeLayout.DragDirections.TOP
        swipeBackground.parentScreenState=BackSwipeLayout.ParentScreenState.FRAGMENT
    }

}
