package com.example.sic100417.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
ImageView img;
String filename="";
Uri imageUri;
int CAMERA_PIC_REQUEST=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.img);
    }
    public void cldk(View view) {
//        Glide.with(this).load(R.raw.c)
//                .into(img);
        Picasso.with(this)
                .load(R.raw.c)
                .placeholder(R.raw.c)
                .into(img);

    }
    }

