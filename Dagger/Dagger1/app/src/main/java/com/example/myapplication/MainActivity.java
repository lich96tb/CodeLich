package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Vehicle vehicle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        VehicleComponent vd = DaggerVehicleComponent.create();

        TextView txtContent = findViewById(R.id.txtContent);
        txtContent.setText("" + vd.provideVehicle().getSpeed());
       // vehicle = component.provideVehicle();


      //  Toast.makeText(this, String.valueOf(vehicle.getSpeed()), Toast.LENGTH_SHORT).show();

    }
}
