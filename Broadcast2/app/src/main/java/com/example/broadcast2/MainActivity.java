package com.example.broadcast2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(mRefreshReceiver, new IntentFilter("My Broadcast"));
        IntentFilter filter = new IntentFilter();
        filter.addAction("My Broadcast");


    }

    private BroadcastReceiver mRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "dddddddddddddd", Toast.LENGTH_SHORT).show();
        }

    };

    public void clickd(View view) {
        Intent intent = new Intent();
        intent.setAction("My Broadcast");
        intent.putExtra("id", 12345);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
