package com.example.lich96tb.broadcastandnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LICH96TB on 5/2/2018.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            NotificationManager notificationManager= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            Intent intenta=new Intent(context,hien_thi_nutification.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,1,intenta,0);

            Notification.Builder noBuilder=new Notification.Builder(context);
            noBuilder.setContentTitle("Thong tin do torng lich").setContentText("ten ban la gi").setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true);
            noBuilder.setContentIntent(pendingIntent);
            // minSdkVersio
            // n 16
            notificationManager.notify(1,noBuilder.build());
        }
    }
}
