package com.melodymediation.sleepstories.adapters
import android.databinding.BindingAdapter
import android.view.View
@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
