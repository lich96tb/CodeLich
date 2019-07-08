package com.example.rxjavaretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.Android
import kotlinx.android.synthetic.main.recycler_row.view.*

class DataAdapter(val list:ArrayList<Android>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false))
    }

    override fun getItemCount(): Int{return list.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_name.text=list.get(position).name
        holder.itemView.tv_version.text=list[position].ver
        holder.itemView.tv_api_level.text=list[position].api
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}