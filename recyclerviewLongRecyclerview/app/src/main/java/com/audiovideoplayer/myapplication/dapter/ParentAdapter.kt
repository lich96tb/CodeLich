package com.audiovideoplayer.myapplication.dapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.audiovideoplayer.myapplication.R
import com.audiovideoplayer.myapplication.model.Parent
import kotlinx.android.synthetic.main.item_parent.view.*

class ParentAdapter(private val parents: List<Parent>) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_parent, p0, false))
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.txtColum.text = parents[p1].name
        p0.itemView.recyclerviewParent.apply {
            adapter = ChildAdapter(parents[p1].children)
            layoutManager = LinearLayoutManager(p0.itemView.context,LinearLayout.HORIZONTAL,false)
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}