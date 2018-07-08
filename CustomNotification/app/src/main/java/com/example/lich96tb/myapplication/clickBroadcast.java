package com.example.lich96tb.myapplication;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class clickBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(intent.getExtras().getInt("id"));
        manager.cancel(intent.getExtras().getInt("next"));
        if (intent.getStringExtra("id")!=null)
        Toast.makeText(context,intent.getStringExtra("id")+"", Toast.LENGTH_SHORT).show();
        if (intent.getStringExtra("next")!=null)
        Toast.makeText(context,intent.getStringExtra("next")+"", Toast.LENGTH_SHORT).show();
    }
}
