package com.example.myapplication


import javax.inject.Inject
//inject được dùng để xác định các phụ thuộc hàm
class Vehicle @Inject
constructor(private var motor: Motor) {
    fun getSpeed():Int{
        return motor.rpm
    }
    fun setSpeed(){
        motor.rpm=11230
    }

}