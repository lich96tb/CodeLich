package com.example.myapplication.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Content
import com.example.myapplication.ContentAdapter

import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_fm1.*
import kotlinx.android.synthetic.main.fragment_fm1.view.*
import kotlinx.android.synthetic.main.fragment_fm2.*

class Fm2 : Fragment() {
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var listData: ArrayList<Content>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fm2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listData = ArrayList()
        contentAdapter = ContentAdapter(addListData())
        recylcerviewContent2.apply {
            adapter = contentAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun addListData(): ArrayList<Content>? {

        listData.add(Content("Kjdksjdkfs", R.drawable.a))
        listData.add(Content("ddd", R.drawable.b))
        listData.add(Content("eee", R.drawable.c))
        listData.add(Content("ffff", R.drawable.d))
        listData.add(Content("yyt", R.drawable.e))
        listData.add(Content("jghjg", R.drawable.f))
        listData.add(Content("kuyu", R.drawable.g))
        listData.add(Content("rtyrtt", R.drawable.h))
        listData.add(Content("gfghgg", R.drawable.i))
        listData.add(Content("nnnnn", R.drawable.c))
        listData.add(Content("tttrr", R.drawable.d))
        listData.add(Content("ggjjj", R.drawable.f))
        listData.add(Content("mmmm", R.drawable.g))
        listData.add(Content("Kjdksjdkfs", R.drawable.a))
        listData.add(Content("ddd", R.drawable.b))
        listData.add(Content("eee", R.drawable.c))
        listData.add(Content("ffff", R.drawable.d))
        listData.add(Content("yyt", R.drawable.e))
        listData.add(Content("jghjg", R.drawable.f))
        listData.add(Content("kuyu", R.drawable.g))
        listData.add(Content("rtyrtt", R.drawable.h))

        return listData
    }
}
