package com.example.lich96tb.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Animation animation;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_an_hien:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_an_hien);
                imageView.startAnimation(animation);
                break;
            case R.id.btn_len_xuong:

                break;
            case R.id.btn_sang_ngang:
                Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_di_chuyen);
                imageView.startAnimation(animation);
                break;
            case R.id.btn_xoay_tron:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_xoay);
                imageView.startAnimation(animation);
                break;
            case R.id.btn_an_hien_xoay:
                animation = AnimationUtils.loadAnimation(this, R.anim.anin_sang_xoay);
                imageView.startAnimation(animation);
                break;
        }
    }
}
