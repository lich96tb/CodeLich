package com.melodymediation.sleepstories.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class BroadcastStopService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, ServiceMedia.class);
        context.stopService(intent1);
        System.exit(0);
    }
}
