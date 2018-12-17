package com.example.abc.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE =12 ;
    private long rxBytes,seBytes;
    private long preRxBytes,preSeBytes;
    private int k=0;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         handler = new Handler();
        handler.post(sendData);


    }
    private Runnable sendData=new Runnable(){
        public void run(){
            try {
                calculateNetSpeed();
                //prepare and send the data here..
                handler.postDelayed(sendData,3000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void calculateNetSpeed() {
        k=k+1;
        rxBytes=TrafficStats.getTotalRxBytes();
        seBytes=TrafficStats.getTotalTxBytes()-rxBytes;
        double downloadSpeed=(rxBytes-preRxBytes)/2;
        double uploadSpeed=(seBytes-preSeBytes)/2;
        preRxBytes=rxBytes;
        preSeBytes=seBytes;
        //根据范围决定显示单位
        String upSpeed=null;
        String downSpeed=null;

        NumberFormat df= java.text.NumberFormat.getNumberInstance();
        df.setMaximumFractionDigits(2);

        if(downloadSpeed>1024*1024){
            downloadSpeed/=(1024*1024);
            downSpeed=df.format(downloadSpeed)+"MB/s";
        }else if(downloadSpeed>1024){
            downloadSpeed/=(1024);
            downSpeed=df.format(downloadSpeed)+"KB/s";
        }else{
            downSpeed=df.format(downloadSpeed)+"B/s";
        }

        if(uploadSpeed>1024*1024){
            uploadSpeed/=(1024*1024);
            upSpeed=df.format(uploadSpeed)+"MB/s";
        }else if(uploadSpeed>1024){
            uploadSpeed/=(1024);
            upSpeed=df.format(uploadSpeed)+"KB/s";
        }else{
            upSpeed=df.format(uploadSpeed)+"B/s";
        }

        Log.e("ABSC "+k," "+downSpeed+" upload "+upSpeed);
     //   updateSpeed("↓ "+downSpeed,"↑ "+upSpeed);
    }

}
