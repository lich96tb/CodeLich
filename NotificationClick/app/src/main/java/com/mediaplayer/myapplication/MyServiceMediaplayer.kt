package com.mediaplayer.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.mediaplayer.myapplication.MyServiceMediaplayer

class MyServiceMediaplayer : Service() {
    override fun onCreate() {
        showNotification1(applicationContext)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e("SSSSSSSSSSSSd ", "kjskdjfkdfd3333333333")
        if (intent.action != null && intent.action == "pausePlay") {
        }
        if (intent.action != null && intent.action == "timerbackground") {
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    inner class MyBinder : Binder() {
        val service: MyServiceMediaplayer
            get() = this@MyServiceMediaplayer
    }

    fun showNotification1(context: Context): Notification {
        val NOTIFICATION_CHANNEL_ID = "ljdk"
        val notification: Notification
        val resultIntent = Intent(context, MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(context,
                0 /* Request code */, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val contentView = RemoteViews(context.packageName, R.layout.notification)
        val mNotificationManager: NotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val nbuilder: NotificationCompat.Builder = NotificationCompat.Builder(context)
        nbuilder.setCustomBigContentView(contentView)
                .setCustomBigContentView(contentView)
                .setSmallIcon(R.mipmap.logo)
                .setCustomContentView(contentView)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("My notification")
                .setContentText("Hello World!")

//        contentView.setTextViewText(R.id.txtNameMTF, nameSong);
//        contentView.setTextViewText(R.id.txtTime, timeSong);

//        contentView.setImageViewBitmap(R.id.imgbackgroundNotification, bitmapBackground);

//        if (playing) {
//            contentView.setImageViewResource(R.id.imgPlay, R.drawable.ic_pause);
//        } else {
//            contentView.setImageViewResource(R.id.imgPlay, R.drawable.ic_play);
//        }
        val button_intent = Intent("pausePlay")
        val pendingIntent = PendingIntent.getService(context, 123, button_intent, 0)
        contentView.setOnClickPendingIntent(R.id.playPauseNotification, pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance)
            nbuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        notification = nbuilder.notification
        mNotificationManager.notify(1, notification)
        return notification
    }
}