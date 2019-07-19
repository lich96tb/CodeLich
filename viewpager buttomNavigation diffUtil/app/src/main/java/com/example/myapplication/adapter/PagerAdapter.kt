package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.fragment.Fm1
import com.example.myapplication.fragment.Fm2
import com.example.myapplication.fragment.Fm3

class PagerAdapter internal constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {
        var frag: Fragment? = null
        when (position) {
            0 -> frag = Fm1()
            1 -> frag = Fm2()
            2 -> frag = Fm3()
        }
        return frag
    }

    override fun getCount(): Int {
        return 3
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        var title = ""
//        when (position) {
//            0 -> title = "One"
//            1 -> title = "Two"
//            2 -> title = "Three"
//        }
//        return title
//    }
}