package com.example.myapplication


import javax.inject.Inject

class Vehicle @Inject
constructor(private var motor: Motor) {
    fun getSpeed():Int{
        return motor.rpm
    }
    fun setSpeed(){
        motor.rpm=11230
    }

    fun ABC(): Int {
        return 13
    }
}