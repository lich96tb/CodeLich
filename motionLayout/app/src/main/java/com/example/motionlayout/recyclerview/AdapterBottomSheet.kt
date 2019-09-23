package com.example.motionlayout.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionlayout.R
import com.example.motionlayout.home.MotionLayoutAdapter
import com.example.motionlayout.utils.ViewHolderNormal
import com.example.motionlayout.utils.fakeData

class AdapterBottomSheet : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderNormal(LayoutInflater.from(parent.context).inflate(R.layout.item_motion_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return fakeData().size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}