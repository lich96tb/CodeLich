package com.example.myapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fomat_number.*
import java.text.DecimalFormat


class FomatNumber : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fomat_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnFomat.setOnClickListener {
            var number = edtNumber.text.trim().toString()
            if(number.isNotEmpty()){
                val formatter = DecimalFormat("#,###,###")
                val yourFormattedString = formatter.format(number.toLong())
                txtNunber.text=""+yourFormattedString
            }
        }
    }


}
