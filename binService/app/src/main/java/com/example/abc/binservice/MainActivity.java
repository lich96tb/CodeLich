package com.example.abc.binservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private boolean isBound = false;
    private MyService myService;
    private Intent intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  startService(intent);
      //  myService = new MyService();
        if (MethodStatic.isMyServiceRunning(MyService.class, this)) {
            Log.e("ABSDf ", "  1");
            //   Toast.makeText(myService, "1", Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(myService, "2", Toast.LENGTH_SHORT).show();
            Log.e("ABSDf ", "  2");
//            intent = new Intent(MainActivity.this, MyService.class);
//            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }


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
            case R.id.btnStartService:
                startService(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        intent = new Intent(MainActivity.this, MyService.class);
        intent.setAction(MyService.START_SERVICE);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}
