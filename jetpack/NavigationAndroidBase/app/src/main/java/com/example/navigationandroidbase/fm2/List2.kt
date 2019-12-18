package com.example.navigationandroidbase.fm2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.navigationandroidbase.R
import kotlinx.android.synthetic.main.fragment_list2.*

/**
 * A simple [Fragment] subclass.
 */
class List2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button4.setOnClickListener {
            it.findNavController().navigate(R.id.fm3)
        }
    }

}
