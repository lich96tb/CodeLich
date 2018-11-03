package com.melodymediation.sleepstories.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by AMBE on 10/15/2018 at 6:04 PM.
 */
public class AlertDialogReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("dkjksjdkf","klsjdkjfd");
        String txt = intent.getStringExtra("STOP");
        if (txt.equals("1203")) {
            Intent background = new Intent(context, ServiceMedia.class);
            context.stopService(background);
        } else {
            Intent alarmIntent = new Intent("android.intent.action.MAIN");
            alarmIntent.setClass(context, AlertDialogClass.class);
            alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Start the popup activity

            context.startActivity(alarmIntent);
        }


    }
}
