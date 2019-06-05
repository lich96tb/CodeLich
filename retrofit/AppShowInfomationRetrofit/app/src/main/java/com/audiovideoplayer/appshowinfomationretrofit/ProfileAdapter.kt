package com.audiovideoplayer.appshowinfomationretrofit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.audiovideoplayer.appshowinfomationretrofit.model.DataItem
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter(val listProfile: List<DataItem>) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false))
    }

    override fun getItemCount(): Int {
        return listProfile.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = listProfile[position]
        holder.itemView.txtName.text = profile.firstName + profile.lastName
        holder.itemView.txtEmail.text = profile.email
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}