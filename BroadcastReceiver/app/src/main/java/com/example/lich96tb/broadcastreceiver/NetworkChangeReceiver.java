package com.example.lich96tb.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LICH96TB on 5/2/2018.
 */

public class NetworkChangeReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("lich96tb")){
            Toast.makeText(context, "do tong lich", Toast.LENGTH_SHORT).show();
            String nd=intent.getStringExtra("lich");
            Toast.makeText(context, ""+nd, Toast.LENGTH_SHORT).show(); ;
        }
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            Toast.makeText(context, "mang on off", Toast.LENGTH_SHORT).show();
        }

    }
}
