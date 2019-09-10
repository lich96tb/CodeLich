package com.example.popupmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val popup = PopupMenu(this@MainActivity, button)
            popup.menuInflater.inflate(R.menu.poupup_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()//showing popup menu
        }
    }
}
