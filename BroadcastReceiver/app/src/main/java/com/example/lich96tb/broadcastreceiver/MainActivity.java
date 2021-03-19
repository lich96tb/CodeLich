package com.example.lich96tb.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khi da khai bao action ben manifest thì chỉ càn gửi
//        button2.setOnClickListener {
//            val intent = Intent(this, NetworkChangeReceiver::class.java)
//            intent.action = "abc"
//            sendBroadcast(intent)
//
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // khoi tao su kien loc broadcast
        initBroadcasReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // huy dang ky broadcast
        unregisterReceiver(networkChangeReceiver);
    }

    private void initBroadcasReceiver() {
         networkChangeReceiver=new NetworkChangeReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("lich96tb");
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    public void chuyen(View view) {
        Intent intent=new Intent();
        intent.putExtra("lich","do torng lich");
        // truyen voa action co ten la lich96tb
        intent.setAction("lich96tb");
        sendBroadcast(intent);
    }
}
