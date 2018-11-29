package com.example.myapplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
String url="https://static.solarjsc.vn/sound/background/background_03.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mp=new MediaPlayer();
        try {
            mp.setDataSource(url);
            mp.prepare();
            Toast.makeText(this, "thoi gian "+mp.getDuration(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

}
