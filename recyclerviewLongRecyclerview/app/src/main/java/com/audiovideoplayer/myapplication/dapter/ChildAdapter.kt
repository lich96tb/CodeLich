package com.audiovideoplayer.myapplication.dapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.audiovideoplayer.myapplication.R
import com.example.framgianguyenthanhtungh.expandablerecycler.model.Child
import kotlinx.android.synthetic.main.item_child.view.*

class ChildAdapter(private val list: List<Child>) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_child, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.txtName.text = list[p1].name
        p0.itemView.txtCost.text = "${list[p1].cost}"
        p0.itemView.setOnClickListener {
            Toast.makeText(p0.itemView.context,"${list[p1].name}}", Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}