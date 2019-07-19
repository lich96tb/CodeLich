package com.example.myapplication.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Content
import com.example.myapplication.ContentAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_fm1.*
import kotlinx.android.synthetic.main.fragment_fm1.view.*
import kotlin.random.Random

class Fm1 : Fragment() {
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var listData: ArrayList<Content>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fm1, container, false)
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        val text = edt1.getText().toString().trim()
//        if (text != null) {
//            outState.putString("text", text)
//        }
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        if (savedInstanceState != null) {
//            val text_old = savedInstanceState.getString("text")
//            edt1.setText(text_old)
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listData = ArrayList()
        contentAdapter = ContentAdapter(addListData())
        recylcerviewContent.apply {
            adapter = contentAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
       view.clickAdd.setOnClickListener {
           var list2=ArrayList<Content>()
           list2.addAll(listData)
           list2.add(0,Content(System.currentTimeMillis().toString(), R.drawable.d,2))
         contentAdapter.addData(list2)
     view.recylcerviewContent.scrollToPosition(0)
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
