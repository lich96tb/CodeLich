package com.example.myapplication
import androidx.recyclerview.widget.DiffUtil

class EmployeeDiffCallback(private var listOldData: ArrayList<Content>, private var listNewData: ArrayList<Content> ): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listOldData[oldItemPosition].name==listNewData[newItemPosition].name
    }

    override fun getOldListSize(): Int {
        return listOldData.size
    }

    override fun getNewListSize(): Int {
       return listNewData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return true
    }


}
