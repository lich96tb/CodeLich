package com.example.myapplication.ImageFilterView


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_image_one_filter_view.*

class ImageOneFilterView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_one_filter_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClick.setOnClickListener {
            if (motionLayout.progress > 0.5f) {
                motionLayout.transitionToStart()
            } else {
                motionLayout.transitionToEnd()
            }
         //   motionLayout.transitionToStart()
        }

        image.setOnClickListener{
            Toast.makeText(context,"kkk ",Toast.LENGTH_LONG).show()
        }
    }


}
