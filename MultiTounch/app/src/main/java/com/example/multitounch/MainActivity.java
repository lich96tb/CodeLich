package com.example.multitounch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    int x, y, x2, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointIndex = event.getActionIndex();
                int maskAction = event.getActionMasked();
                switch (maskAction) {
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        if (pointIndex >= 1) {
                            //  Log.e("ABC2bbbb", event.getX(1)-event.getX(0) + "");
                            x = (int) Math.abs(event.getX(1) - event.getX(0));
                            y = (int) Math.abs(event.getX(1) - event.getX(0));
                            Log.e("ADD","hgj");
                        }
                    }
                    case MotionEvent.ACTION_MOVE: {
                        for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                            if (i >= 1) {
//                        Log.e("ABC2",(event.getX(1)-event.getX(0)) +"");
                                x2 = (int) Math.abs(event.getX(1) - event.getX(0));
                                y2 = (int) Math.abs(event.getX(1) - event.getX(0));
                                if (x2-x>10||y2-y>10){
                                    Log.e("ABC","phong");
                                    Toast.makeText(MainActivity.this, "phong", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "thu", Toast.LENGTH_SHORT).show();
                                    Log.e("ABC","thu");
                                }
                            }
                        }
                        break;
                    }
                }
                return true;
            }
        });
    }
}
