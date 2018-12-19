package com.example.admin.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.loginapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, new FragmentLogin()).commit();
    }
}
