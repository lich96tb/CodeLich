package com.example.lich96tb.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
      NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intenta=new Intent(this,hien_thi_nutification.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intenta,0);

      Notification.Builder noBuilder=new Notification.Builder(this);
      noBuilder.setContentTitle("Thong tin do torng lich").setContentText("ten ban la gi").setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true);
      noBuilder.setContentIntent(pendingIntent);
      // minSdkVersio
        // n 16
      notificationManager.notify(1,noBuilder.build());
    }
}
