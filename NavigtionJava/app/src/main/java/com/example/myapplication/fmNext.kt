package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_fm_next.*

class fmNext : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
var view=inflater.inflate(R.layout.fragment_fm_next, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtNext.text=arguments?.getString("nameArg")
        btnabc.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack(R.id.fmNext, true)
        }
    }


}
