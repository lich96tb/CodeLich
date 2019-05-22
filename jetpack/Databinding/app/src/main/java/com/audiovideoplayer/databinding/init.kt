package com.audiovideoplayer.databinding

import android.content.Context
import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.Toast
import com.audiovideoplayer.databinding.adapter.AdapterStudent
import com.bumptech.glide.Glide

@BindingAdapter("app:loadImageUrl")
fun loadImageUrl(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}
@BindingAdapter("app:setAdapter")
fun setAdapter(view: RecyclerView, adapterStudent: AdapterStudent) {
    view.adapter = adapterStudent
    view.hasFixedSize()
    view.layoutManager = LinearLayoutManager(view.context)
}
fun toastShort(context: Context, content:String){
    Toast.makeText(context,content,Toast.LENGTH_SHORT).show()
}
