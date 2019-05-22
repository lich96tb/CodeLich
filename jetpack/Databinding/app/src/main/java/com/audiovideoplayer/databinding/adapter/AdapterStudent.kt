package com.audiovideoplayer.databinding.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.audiovideoplayer.databinding.R
import com.audiovideoplayer.databinding.interfaces.ItemClick
import kotlinx.android.synthetic.main.item_student.view.*

class AdapterStudent(var list: ArrayList<String>,var itemClick: ItemClick) : RecyclerView.Adapter<AdapterStudent.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_student, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.txtName.text=list[p1]
        p0.itemView.setOnClickListener {itemClick.itemClick(p0.adapterPosition) }
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

}