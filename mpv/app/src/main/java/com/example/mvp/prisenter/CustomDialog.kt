package com.example.mvp.prisenter

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.example.mvp.R
import kotlinx.android.synthetic.main.dialog.*

class CustomDialog(dialog: Dialog, context: Context) {
    init {
        dialog.setContentView(R.layout.dialog)
        dialog.show()
        dialog.btn1.setOnClickListener {
            Toast.makeText(context, "click 1", Toast.LENGTH_LONG).show()
        }
        dialog.btn2.setOnClickListener {
            Toast.makeText(context, "click 2", Toast.LENGTH_LONG).show()
        }
    }

}