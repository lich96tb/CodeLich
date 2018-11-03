package com.melodymediation.sleepstories.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.melodymediation.sleepstories.service.evenbust.MessegaeEventService;

import org.greenrobot.eventbus.EventBus;

public class ClickBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(intent.getExtras().getInt("id"));

        if (intent.getStringExtra("id") != null) {

            if (ServiceMedia.checkPlay) {
                EventBus.getDefault().postSticky(new MessegaeEventService(false, 0));
            } else {
                EventBus.getDefault().postSticky(new MessegaeEventService(true, 0));
            }
        }

//        //close notificaton bar
//        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//        context.sendBroadcast(it);
//
//        //xoa nutification
//        NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notifManager.cancelAll();
        //dsfsd


    }

}
