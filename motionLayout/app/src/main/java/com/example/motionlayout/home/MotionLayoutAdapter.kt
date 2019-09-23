package com.example.motionlayout.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motionlayout.R
import com.example.motionlayout.bottom_sheet.BottomSheet
import com.example.motionlayout.replace_fragment.SwipeReplaceFragment
import com.example.motionlayout.swipe_along.SwipeAlong
import com.example.motionlayout.swipe_landscape.SwipeLandscape
import kotlinx.android.synthetic.main.item_motion_layout.view.*

class MotionLayoutAdapter(var listMotionLayout: ArrayList<String>, var layoutmanager: FragmentManager) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderNormal(LayoutInflater.from(parent.context).inflate(R.layout.item_motion_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return listMotionLayout.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            txtNameMotion.text = listMotionLayout[position]

        }
        holder.itemView.setOnClickListener {
            when (holder.adapterPosition) {
                0 -> layoutmanager.beginTransaction().replace(R.id.mainActivity, SwipeAlong()).addToBackStack(null).commit()
                1 -> layoutmanager.beginTransaction().replace(R.id.mainActivity, SwipeLandscape()).addToBackStack(null).commit()
                2 -> layoutmanager.beginTransaction().replace(R.id.mainActivity, SwipeReplaceFragment()).addToBackStack(null).commit()
                3 -> layoutmanager.beginTransaction().replace(R.id.mainActivity, BottomSheet()).addToBackStack(null).commit()
            }
        }
    }

    class ViewHolderNormal(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}