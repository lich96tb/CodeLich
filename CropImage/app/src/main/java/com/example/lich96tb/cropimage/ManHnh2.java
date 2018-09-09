package com.example.lich96tb.cropimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ManHnh2 extends AppCompatActivity {
ImageView img;
static Bitmap mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hnh2);
        img=findViewById(R.id.img);
        img.setImageBitmap(mImage);
    }
}
