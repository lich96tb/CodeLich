package com.example.recyclerviewborder

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_infomation.view.*

class AdapterInformation(var list: ArrayList<information>) :
    RecyclerView.Adapter<AdapterInformation.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_infomation,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txtName.text = list[position].name
        Glide.with(holder.itemView.context).load(list[position].img).into(holder.itemView.imgAvata)
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)


}