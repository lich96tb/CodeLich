package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_content.view.*
import android.text.method.TextKeyListener.clear
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback


class ContentAdapter(var list: ArrayList<Content>? = null) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    val PAYLOAD_NAME = "PAYLOAD_NAME"
    private var list2: ArrayList<Content>? = null

    init {
        list?.forEach {
            list2 = ArrayList()
            list2?.addAll(list!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))
    }


    override fun getItemCount(): Int {
        return list!!.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txtName.text = list?.get(position)?.name
        Glide.with(holder.itemView.context).load(list?.get(position)?.imageAvata).into(holder.itemView.imgAvata)
        holder.itemView.setOnClickListener {
            list2?.remove(list2!![holder.adapterPosition])
            syncBookmarkedItems()
           // notifyDataSetChanged()
        }
    }
    fun addData(listAdd:ArrayList<Content>){
        list2?.clear()
        list2?.addAll(listAdd)
        syncBookmarkedItems()
    }


    private fun syncBookmarkedItems() {
        Log.e("kkkkkkk","  "+list!!.size   +" list2    ${list2!!.size}")
        val callback = EmployeeDiffCallback(list!!, list2!!)
        val diffResult = DiffUtil.calculateDiff(callback!!)
        list?.clear()
        list!!.addAll(list2!!)

        diffResult.dispatchUpdatesTo(object :ListUpdateCallback{
            override fun onChanged(position: Int, count: Int, payload: Any?) {
                Log.e("AAAAAAAAAd","kkk 1")
                notifyItemRangeChanged(position + 1, count, payload)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                Log.e("AAAAAAAAAd","kkk 2")
                notifyItemMoved(fromPosition + 1, toPosition + 1)
            }

            override fun onInserted(position: Int, count: Int) {
                Log.e("AAAAAAAAAd","kkk 3")
                notifyItemRangeInserted(position , count)
            }

            override fun onRemoved(position: Int, count: Int) {
                Log.e("AAAAAAAAAd", "kkk 4   $count")
                notifyItemRangeRemoved(position, count)
            }
        })

    }


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
}