package com.audiovideoplayer.databinding

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.audiovideoplayer.databinding.adapter.AdapterStudent
import com.audiovideoplayer.databinding.databinding.ActivityMainBinding
import com.audiovideoplayer.databinding.interfaces.ItemClick

class MainActivity : AppCompatActivity(), ItemClick {
    var url= ObservableField<String>("https://kenh14cdn.com/2017/212310618041193431006884986278710988847462n-1507458620893.jpg")
    var adapterStudent= ObservableField<AdapterStudent>()

    override fun itemClick(position: Int) {
        toastShort(this, position.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val main =MainActivity()
        binding.main=main
        binding.lifecycleOwner = this
        var list = ArrayList<String>()
        for (k: Int in 1..20) {
            list.add("abc $k")
        }
        main.adapterStudent.set(AdapterStudent(list, this))


    }
}
