package com.audiovideoplayer.appshowinfomationretrofit.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.audiovideoplayer.appshowinfomationretrofit.R
import com.audiovideoplayer.appshowinfomationretrofit.model.DataItem
import com.audiovideoplayer.appshowinfomationretrofit.ui.SingleUserActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter(val listProfile: List<DataItem>) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_profile, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listProfile.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = listProfile[position]
        holder.itemView.txtName.text = profile.first_name + profile.last_name
        holder.itemView.txtEmail.text = profile.email
        Glide.with(holder.itemView.imgAvata.context).load(profile.avatar).into(holder.itemView.imgAvata)
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,SingleUserActivity::class.java)
            intent?.putExtra("id",profile.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}