package com.example.lich96tb.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private NotificationManager notificationManager;
    int NOTIFICATION_ID  = 101;
    RemoteViews layout = null;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {

        Notification notification =new  Notification(R.drawable.ic_launcher_background, "Eye Protecter", NOTIFICATION_ID);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(AppCompatActivity.NOTIFICATION_SERVICE);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);
        contentView.setTextViewText(R.id.btn,"lichd");
        contentView.setImageViewResource(R.id.imgNoti,R.drawable.logo);
        contentView.setTextViewText(R.id.txt1, "BLUE LIGHT FILTER is OFF");
        contentView.setTextViewText(R.id.txt2, "Tap to turn ON");
        notification.contentView = contentView;


        Intent button_intent=new Intent("button");
        button_intent.putExtra("id","lichddd");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,123,button_intent,0);
        contentView.setOnClickPendingIntent(R.id.btn,pendingIntent);

        Intent intent=new Intent("next");
        intent.putExtra("next","nextfff");
        PendingIntent pendingIntenta=PendingIntent.getBroadcast(this,123,intent,0);
        contentView.setOnClickPendingIntent(R.id.btnNext,pendingIntenta);



        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_LIGHTS;
        mNotificationManager.notify(1, notification);

    }
}
