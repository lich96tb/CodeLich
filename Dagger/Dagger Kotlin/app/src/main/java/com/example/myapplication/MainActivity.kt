package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var vd = DaggerVehicleComponent.builder().vehicleModule(VehicleModule()).build()
        vd.provideVehicle().setSpeed()
        txtNames.setText("" + vd.provideVehicle().getSpeed())

        Toast.makeText(this, "" + vd.provideVehicle().getSpeed(), Toast.LENGTH_SHORT).show()
    }
}
