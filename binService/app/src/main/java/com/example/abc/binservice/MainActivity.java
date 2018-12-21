package com.example.abc.binservice;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private boolean isBound = false;
    private MyService myService;
    private Intent intent;
//    private ServiceConnection connection = new ServiceConnection() {
//
//        // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            isBound = false;
//        }
//
//        // Phương thức này được hệ thống gọi khi kết nối tới service thành công
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService.MyBinder binder = (MyService.MyBinder) service;
//            myService = binder.getService(); // lấy đối tượng MyService
//            isBound = true;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myService = new MyService();
        //  intent = new Intent(MainActivity.this, MyService.class);

        // startService(intent);
        //    bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {

        // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }

        // Phương thức này được hệ thống gọi khi kết nối tới service thành công
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService(); // lấy đối tượng MyService
            isBound = true;
        }
    };


    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:

                myService.play();

                break;

            case R.id.btnPause:
                myService.pause();


                break;
            case R.id.btnNext:
                myService.seekto();
                break;
            case R.id.btnHiden:
                myService.hidenNotification();
                break;
            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnDisconnectSevice:
                // unbindService(connection);
                //stopService(intent);


                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                if(myService.activity != null) {
//                    ServiceCallingActivity.activity.finish();
//                }
                if (isBound) {
                    unbindService(connection);
                    isBound = false;
                }

                stopService(intent);
                break;
        }
    }

//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }


    @Override
    protected void onStart() {
        super.onStart();
        if (intent == null) {
            intent = new Intent(this, MyService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            startService(intent);
        }
    }




}
