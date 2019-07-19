package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.adapter.PagerAdapter
import com.example.myapplication.fragment.Fm1
import com.example.myapplication.fragment.Fm2
import com.example.myapplication.fragment.Fm3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private lateinit var fragment:Fragment
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //supportFragmentManager.beginTransaction().replace(R.id.homeFragment, Fm1()).commit()
                viewPager.currentItem=0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
              //  supportFragmentManager.beginTransaction().replace(R.id.homeFragment, Fm2()).commit()
                viewPager.currentItem=1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                viewPager.currentItem=2
             //  supportFragmentManager.beginTransaction().replace(R.id.homeFragment, Fm3()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment=Fm1()
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        viewPager.adapter=PagerAdapter(supportFragmentManager)
      //  viewPager.beginFakeDrag();
        //enable swiping
       // viewPager.endFakeDrag();

    }
}
