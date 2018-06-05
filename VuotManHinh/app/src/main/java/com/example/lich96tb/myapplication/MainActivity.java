package com.example.lich96tb.myapplication;

import android.gesture.Gesture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private int positon = 0;
    GestureDetector gestureDetector;
    int[] manhinh = {R.drawable.a, R.drawable.c, R.drawable.g, R.drawable.e};
    int KHOANGCACHVUOT = 100;
    int VANTOC = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.image);
        img.setImageResource(manhinh[positon]);
        gestureDetector = new GestureDetector(this, new Mygestue());
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    class Mygestue extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //keo tu trai sang phai
            if (e2.getX() - e1.getX() > KHOANGCACHVUOT && Math.abs(velocityX) > VANTOC) {
                Toast.makeText(MainActivity.this, "vuot tra sang phai", Toast.LENGTH_SHORT).show();
                if (positon > 0)
                    positon--;
                img.setImageResource(manhinh[positon]);


            }
            if (e2.getX() - e1.getX() < KHOANGCACHVUOT && Math.abs(velocityX) > VANTOC) {
                Toast.makeText(MainActivity.this, "vuot tra sang trai", Toast.LENGTH_SHORT).show();
                if (positon < manhinh.length - 1)
                    positon++;
                img.setImageResource(manhinh[positon]);
            }

            Log.e("position", positon + "");


            return super.onFling(e1, e2, velocityX, velocityY);

        }
    }
}

