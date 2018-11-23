package com.example.stolar_lich96tb.autorunservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MsgPushService extends Service {
    Handler handler = new Handler();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.postDelayed(sendData,1000);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    private Runnable sendData=new Runnable(){
        public void run(){
            try {
                //prepare and send the data here..
                Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_LONG).show();
                handler.removeCallbacks(sendData);
                handler.postDelayed(sendData, 1000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
