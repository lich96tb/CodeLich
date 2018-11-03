package com.example.bemboy.kjsadkbjfsdklfsdf;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                .setContent(remoteViews)
                .setAutoCancel(true)
                .setSound(null)
                .setWhen(0)
                .setPriority(NotificationManager.IMPORTANCE_LOW);

        Intent button_intent = new Intent("NightMode");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, button_intent, 0);
        Intent button_intent1 = new Intent("Flash");
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, 123, button_intent1, 0);
        Intent button_intent2 = new Intent("Sound");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 123, button_intent2, 0);


        remoteViews.setOnClickPendingIntent(R.id.swtNightModeNo, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.imgFlash, pendingIntent1);
        remoteViews.setOnClickPendingIntent(R.id.imgSound, pendingIntent2);
        notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, mBuilder.build());


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
