package com.example.myapplication

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class VehicleModule {
    //cung cấp các đối tượng cho phụ thuộc hàm

//    @Provides
//    @Singleton
//    internal fun provideMotor(): Motor {
//        return Motor()
//    }

    @Provides
    @Singleton
    internal fun provideVehicle(): Vehicle {
        return Vehicle(Motor())
    }
}