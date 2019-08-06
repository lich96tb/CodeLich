package com.example.myapplication


import dagger.Component
import javax.inject.Singleton

// component định nghĩa kết nối giữa các nhà cung cấp

@Singleton
@Component(modules = [VehicleModule::class])
interface VehicleComponent {

    fun provideVehicle(): Vehicle

}