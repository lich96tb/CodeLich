package com.example.asus.viewswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
private ViewSwitcher vs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vs=findViewById(R.id.vs);
        Animation in= AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out= AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        vs.setAnimation(in);
        vs.setAnimation(out);

    }

    public void ClickButton(View view) {
    vs.showNext();
    }

}
