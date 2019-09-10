package com.example.motionlayout.swipe_along


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout

import com.example.motionlayout.R
import com.example.motionlayout.fragment_children.ListFragment
import com.example.motionlayout.fragment_children.MainFragment
import kotlinx.android.synthetic.main.fragment_swipe_along.*
import kotlin.math.abs

class SwipeAlong : Fragment(), MotionLayout.TransitionListener {
    private lateinit var fragments: Fragment
    private var lastProgress = 0f
    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        if (p3 - lastProgress > 0) {
            // from start to end
            val atEnd = abs(p3 - 1f) < 0.1f
            if (atEnd && fragments is MainFragment) {
                fragments=ListFragment()
                val transaction = fragmentManager!!.beginTransaction()
                transaction.setCustomAnimations(R.animator.show, 0)
                transaction.replace(R.id.container,ListFragment()).addToBackStack(null).commit()
            }
        } else {
            // from end to start
            val atStart = p3 < 0.9f
            if (atStart && fragments is ListFragment) {
                fragments=MainFragment()
                val transaction = fragmentManager!!.beginTransaction()
                transaction.setCustomAnimations(0, R.animator.hide)
                transaction.replace(R.id.container,MainFragment()).addToBackStack(null).commit()
            }
        }
        lastProgress = p3
    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {}

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_swipe_along, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        motionLayout.setTransitionListener(this)
        fragments = MainFragment()
        fragmentManager!!.beginTransaction().replace(R.id.container,fragments).commit()
    }


}
