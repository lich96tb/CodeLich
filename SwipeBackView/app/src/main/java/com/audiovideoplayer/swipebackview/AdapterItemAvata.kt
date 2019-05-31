package com.audiovideoplayer.swipebackview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.audiovideoplayer.swipebackview.interfaces.ClickItem
import kotlinx.android.synthetic.main.item_avata.view.*

class AdapterItemAvata(val listAvata: List<Int>,var clickItem: ClickItem) : RecyclerView.Adapter<AdapterItemAvata.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_avata, p0, false))
    }

    override fun getItemCount(): Int {
        return listAvata.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.imgItemAvata.setImageResource(listAvata[p1])
        p0.itemView.setOnClickListener { clickItem.clickItemImage(listAvata[p0.adapterPosition]) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}