package com.example.motionlayout.bottom_sheet


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.viewpager.widget.ViewPager

import com.example.motionlayout.R
import com.example.motionlayout.home.MotionLayoutAdapter
import com.example.motionlayout.recyclerview.AdapterBottomSheet
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheet : Fragment() {
    private var statusSwipe = R.id.end

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcBottomSheet.apply {
            adapter=AdapterBottomSheet()
        }
        btnBottomSheet.setOnClickListener {
            statusSwipe = if (statusSwipe==R.id.end) {
                motionLayoutBottomSheet.transitionToEnd()
                R.id.start
            } else {
                motionLayoutBottomSheet.transitionToStart()
                R.id.end
            }
        }
        seekbars.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2){
                    motionLayoutBottomSheet.progress= p1.toFloat()/100
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        motionLayoutBottomSheet.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) { statusSwipe=p1 }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) { seekbars.progress = (p3 * 100).toInt() }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {}
        })
    }


}
