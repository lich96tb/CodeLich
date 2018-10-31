package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    String CHANNEL_ID = "doidk";
    int notificationId = 10;
    NotificationCompat.Builder mBuilder;
    RemoteViews remoteViews;
    NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.a2)
                .setContentTitle("khond")
                .setContentText("dkjkdsjkdf")
                .setContent(remoteViews)
                .setAutoCancel(true)
                .setSound(null)
                .setWhen(0)
           //     .setSound(null, Integer.parseInt(null))
                .setPriority(NotificationManager.IMPORTANCE_LOW);
               // .setPriority(NotificationCompat.PRIORITY_DEFAULT);
         notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, mBuilder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void cldidj(View view) {
        remoteViews.setTextViewText(R.id.txtNameMTF, "do trong lcih");
        notificationManager.notify(notificationId, mBuilder.build());

//        Notification notification = mBuilder.build();
//        notification.defaults=0;
//        notification.notify();
       // notification.bigContentView = remoteViews;
      //  startForeground(1, notification);
    }
}
