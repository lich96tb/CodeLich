package com.example.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends BaseActivity {



    @Override
    protected void init() {
        LineVisualizer lineVisualizer = findViewById(R.id.visualizer);
        // set custom color to the line.
        lineVisualizer.setColor(ContextCompat.getColor(this, R.color.custom));

        // set the line with for the visualizer between 1-10 default 1.
        lineVisualizer.setStrokeWidth(1);

        // Set you media player to the visualizer.
        lineVisualizer.setPlayer(mediaPlayer.getAudioSessionId());
    }

    public void replay(View view) {
        playPauseBtnClicked((ImageButton) view);
    }

    public void playPause(View view) {
        playPauseBtnClicked((ImageButton) view);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
