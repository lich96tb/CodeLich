package com.example.motionlayout.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.motionlayout.R
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {
    private var listMotionLayout = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addData()
        rcMotionLayout.apply {
            adapter = MotionLayoutAdapter(listMotionLayout,fragmentManager!!)
            setHasFixedSize(true)
        }
    }

    private fun addData() {
        listMotionLayout.add("vuot len tren")
        listMotionLayout.add("keo sang ngang")
        listMotionLayout.add("vuot thay doi fragment")
    }


}
