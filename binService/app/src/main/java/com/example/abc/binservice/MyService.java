package com.example.abc.binservice;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String NOTIFICATION_CHANNEL_ID = "ljdk";
    private static final int OPENVPN_STATUS = 1;
    private MyPlay myPlay;
    private IBinder binder;
    public static final String START_SERVICE = "de.blinkt.openvpn.START_SERVICE";
    private NotificationManager mNotificationManager;
    private Notification.Builder nbuilder;
    private Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("aaaaaaaaaaaaaaa ","2222222222");
        binder = new MyBinder();
        myPlay = new MyPlay(this);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification1();
        return START_STICKY;
    }

    private void showNotification1() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nbuilder = new Notification.Builder(this);
        nbuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            nbuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        // mNotificationManager.notify(0, nbuilder.build());

        notification = nbuilder.getNotification();
        startForeground(OPENVPN_STATUS, notification);

    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public void play() {
        myPlay.play();
    }

    public void pause() {
        myPlay.pause();
    }

    public void seekto() {
        myPlay.seekto();
    }

    public void hidenNotification() {
        stopForeground(false);
        mNotificationManager.notify(OPENVPN_STATUS, notification);
        //hủy hẳn notificcation
        //mNotificationManager.cancel(OPENVPN_STATUS);
    }


    class MyPlay {
        private MediaPlayer mediaPlayer;

        //them bai hat
        private MyPlay(Context context) {
            mediaPlayer = MediaPlayer.create(context, R.raw.nguoitungthuon);
            mediaPlayer.setLooping(true);
        }

        private void play() {
            mediaPlayer.start();
        }

        public void pause() {
            mediaPlayer.pause();

        }

        public void seekto() {
            mediaPlayer.seekTo(120000);
        }
    }
}
