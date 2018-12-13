package com.example.abc.binservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String NOTIFICATION_CHANNEL_ID = "ljdk";
    private static final int OPENVPN_STATUS = 1;
    private MyPlay myPlay;
    private IBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        myPlay = new MyPlay(this);
        showNotification();


    }

    @Override
    public IBinder onBind(Intent intent) {
        play();
        return binder;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        Toast.makeText(this, "dddddddd123", Toast.LENGTH_SHORT).show();
//        return START_STICKY;
//    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ADDDD ", " onDestroy");
    }


    private void showNotification() {

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        //int icon = getIconByConnectionStatus(status);
        int icon = R.drawable.ic_launcher_background;
        android.app.Notification.Builder nbuilder = new Notification.Builder(this);

        nbuilder.setContentTitle(getString(R.string.notifcation_title_notconnect));

        nbuilder.setContentText("kskjkdfsd");
        nbuilder.setOnlyAlertOnce(true);
        nbuilder.setOngoing(true);
        //   nbuilder.setContentIntent(getLogPendingIntent());
        nbuilder.setSmallIcon(icon);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            nbuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }




//        // Try to set the priority available since API 16 (Jellybean)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
//            jbNotificationExtras(lowpriority, nbuilder);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            lpNotificationExtras(nbuilder);


        @SuppressWarnings("deprecation")
        Notification notification = nbuilder.getNotification();


        mNotificationManager.notify(OPENVPN_STATUS, notification);
        startForeground(OPENVPN_STATUS, notification);


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
            mediaPlayer.seekTo(10000);
        }
    }
}
