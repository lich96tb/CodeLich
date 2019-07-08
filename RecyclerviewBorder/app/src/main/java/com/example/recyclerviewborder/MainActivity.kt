package com.example.recyclerviewborder

import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.opengl.ETC1.getWidth
import android.view.View


class MainActivity : AppCompatActivity() {
    private lateinit var list: ArrayList<information>
    private lateinit var adapterInformation: AdapterInformation
    private var layoutManagers: GridLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = ArrayList()
        addData()
        adapterInformation = AdapterInformation(list)
        layoutManagers = GridLayoutManager(this@MainActivity, 2)



        var itemDecoraoer = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
       // itemDecoraoer.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.lines_vertical)!!)
        recyclerviewGirl.addItemDecoration(itemDecoraoer)

        var itemHorizontal = DividerItemDecoration(this@MainActivity, DividerItemDecoration.HORIZONTAL)
      //  itemHorizontal.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.lines_horizontal)!!)
        recyclerviewGirl.addItemDecoration(itemHorizontal)

        recyclerviewGirl.addItemDecoration(DividerItemDecoration(this@MainActivity, layoutManagers!!.orientation))


        recyclerviewGirl.addItemDecoration(object :
            DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL) {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {


            }

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
                    outRect.setEmpty()
                }
              else
                    super.getItemOffsets(outRect, view, parent, state);
            }
        })



        recyclerviewGirl.apply {
            adapter = adapterInformation
            layoutManager = layoutManagers
            setHasFixedSize(true)
        }


    }

    private fun addData() {
        list.add(information("q", R.drawable.a))
        list.add(information("x", R.drawable.b))
        list.add(information("ds", R.drawable.c))
        list.add(information("dsf", R.drawable.d))
        list.add(information("cxcv", R.drawable.e))
        list.add(information("gfht", R.drawable.f))
        list.add(information("kk", R.drawable.g))
        list.add(information("kkk", R.drawable.h))
        list.add(information("uu", R.drawable.j))
        list.add(information("gg", R.drawable.h))
        list.add(information("uk", R.drawable.a))
        list.add(information("l;", R.drawable.c))
        list.add(information("/;uo", R.drawable.d))
        list.add(information("7uu", R.drawable.g))
        list.add(information("5eer", R.drawable.h))
        list.add(information("kku", R.drawable.d))
        list.add(information(",uiu", R.drawable.h))
        list.add(information("erg", R.drawable.d))
        list.add(information("jkjg", R.drawable.c))
        list.add(information("q", R.drawable.a))
        list.add(information("x", R.drawable.b))
        list.add(information("ds", R.drawable.c))
        list.add(information("dsf", R.drawable.d))
        list.add(information("cxcv", R.drawable.e))
        list.add(information("gfht", R.drawable.f))
        list.add(information("kk", R.drawable.g))
        list.add(information("kkk", R.drawable.h))
        list.add(information("uu", R.drawable.j))
        list.add(information("gg", R.drawable.h))
        list.add(information("uk", R.drawable.a))
        list.add(information("l;", R.drawable.c))
        list.add(information("/;uo", R.drawable.d))
        list.add(information("7uu", R.drawable.g))
        list.add(information("5eer", R.drawable.h))
        list.add(information("kku", R.drawable.d))
        list.add(information(",uiu", R.drawable.h))
        list.add(information("erg", R.drawable.d))
        list.add(information("jkjg", R.drawable.c))
        list.add(information("q", R.drawable.a))
        list.add(information("x", R.drawable.b))
        list.add(information("ds", R.drawable.c))
        list.add(information("dsf", R.drawable.d))
        list.add(information("cxcv", R.drawable.e))
        list.add(information("gfht", R.drawable.f))
        list.add(information("kk", R.drawable.g))
        list.add(information("kkk", R.drawable.h))
        list.add(information("uu", R.drawable.j))
        list.add(information("gg", R.drawable.h))
        list.add(information("uk", R.drawable.a))
        list.add(information("l;", R.drawable.c))
        list.add(information("/;uo", R.drawable.d))
        list.add(information("7uu", R.drawable.g))
        list.add(information("5eer", R.drawable.h))
        list.add(information("kku", R.drawable.d))
        list.add(information(",uiu", R.drawable.h))
        list.add(information("erg", R.drawable.d))
        list.add(information("jkjg", R.drawable.c))
    }
}
