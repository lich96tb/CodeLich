package com.example.lich96tb.bothc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmRecever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("lich96tb","xin chao");
        String chuoi=intent.getExtras().getString("extra");
        Intent myIntent=new Intent(context,Music.class);
        myIntent.putExtra("extra",chuoi);
        context.startService(myIntent);
    }
}
