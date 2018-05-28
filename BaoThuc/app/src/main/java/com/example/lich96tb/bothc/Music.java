package com.example.lich96tb.bothc;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class Music extends Service {
    private MediaPlayer mediaPlayer;
    private int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("chay nghe nhac","ok");
        String nhankey=intent.getExtras().getString("extra");
        if (nhankey.equals("on")){
            mediaPlayer=MediaPlayer.create(this,R.raw.anhnang);
            mediaPlayer.start();
        }else if (nhankey.equals("off")){
            mediaPlayer.stop();
         //   mediaPlayer.reset();
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
