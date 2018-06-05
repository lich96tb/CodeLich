package com.example.lich96tb.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ManHinh2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        String ten= getIntent().getStringExtra("ten");
        Toast.makeText(this, ""+ten, Toast.LENGTH_SHORT).show();
    }
}
