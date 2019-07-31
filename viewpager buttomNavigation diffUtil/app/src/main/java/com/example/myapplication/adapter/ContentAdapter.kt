package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_content.view.*


class ContentAdapter(var list: ArrayList<Content>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPENORMAL = 1
    private val TYPE_ACTIVE = 2
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.txtName.text = list?.get(position)?.name
        Glide.with(holder.itemView.context).load(list?.get(position)?.imageAvata).into(holder.itemView.imgAvata)
        holder.itemView.setOnClickListener {
            list2?.remove(list2!![holder.adapterPosition])
            syncBookmarkedItems()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list?.get(position)?.typer == TYPE_ACTIVE) {
            TYPE_ACTIVE
        } else {
            TYPENORMAL
        }

    }

    private var list2: ArrayList<Content>? = null

    init {
        list?.forEach {
            list2 = ArrayList()
            list2?.addAll(list!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPENORMAL) {
            ViewHolderNormal(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))
        } else {
            ViewHolderActive(LayoutInflater.from(parent.context).inflate(R.layout.item_content_active, parent, false))
        }
    }


    override fun getItemCount(): Int {
        return list!!.size
    }
    fun addData(listAdd: ArrayList<Content>) {
        notifyItemInserted(list!!.size)
      //  list2?.clear()
       // list2?.addAll(listAdd)
      //  list2?.addAll(listAdd)
       // syncBookmarkedItems()
    }


    private fun syncBookmarkedItems() {
        val callback = EmployeeDiffCallback(list!!, list2!!)
        val diffResult = DiffUtil.calculateDiff(callback!!)
        list?.clear()
        list!!.addAll(list2!!)

        diffResult.dispatchUpdatesTo(object : ListUpdateCallback {
            override fun onChanged(position: Int, count: Int, payload: Any?) {
                notifyItemRangeChanged(position + 1, count, payload)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition + 1, toPosition + 1)
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }
        })

    }


    class ViewHolderNormal(itemview: View) : RecyclerView.ViewHolder(itemview)
    class ViewHolderActive(itemview: View) : RecyclerView.ViewHolder(itemview)
}