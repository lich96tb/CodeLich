package com.example.navigationandroidbase.fm2


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.navigationandroidbase.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_fm2.*

class FM2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fm2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = AdapterViewPager(this)

        TabLayoutMediator(tabLayout, viewpager, false) { tab, position ->
            tab.setIcon(
                when (position) {
                    0 -> R.drawable.garden_tab_selector
                    1 -> R.drawable.plant_list_tab_selector
                    else -> throw IndexOutOfBoundsException()
                }
            )
            tab.text = when (position) {
                0 -> "tab a"
                1 -> "tab b"
                else -> throw IndexOutOfBoundsException()
            }
        }.attach()

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.i("autolog", "position: " + position);

            }
        })

    }


}
