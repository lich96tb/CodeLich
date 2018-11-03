package com.melodymediation.sleepstories.adapters

import android.support.v7.util.DiffUtil
import com.melodymediation.sleepstories.data.room.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem?.name == newItem?.name
    }

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem == newItem
    }
}
