package com.example.myapplication


import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [VehicleModule::class])
interface VehicleComponent {

    fun provideVehicle(): Vehicle

}