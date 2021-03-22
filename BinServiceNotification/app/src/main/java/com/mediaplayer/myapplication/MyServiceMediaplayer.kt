package com.mediaplayer.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

public class MyServiceMediaplayer : Service() {
    private lateinit var nbuilder: NotificationCompat.Builder
    private lateinit var contentView: RemoteViews
    private var binder: IBinder? = null
    private var myPlay: MyPlay? = null
    override fun onCreate() {
        Log.e("sssssssssss ", "start")
        binder = MyBinder()
        myPlay = MyPlay(applicationContext)
    }

    inner class MyBinder : Binder() {
        fun getService(): MyServiceMediaplayer {
            return this@MyServiceMediaplayer
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (myPlay!!.isPlay()) {
            myPlay!!.pause()
        } else {
            myPlay!!.play()
        }
        showNotification1(applicationContext)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }


    lateinit var mNotificationManager: NotificationManager
    lateinit var notification: Notification
    fun showNotification1(context: Context): Notification {
        val NOTIFICATION_CHANNEL_ID = "ljdk"

        val resultIntent = Intent(context, MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        contentView = RemoteViews(context.packageName, R.layout.notification)
        mNotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nbuilder = NotificationCompat.Builder(context)
        nbuilder.setCustomBigContentView(contentView)
            .setCustomBigContentView(contentView)
            .setSmallIcon(R.mipmap.logo)
            .setCustomContentView(contentView)
            .setContentIntent(resultPendingIntent)

//        contentView.setTextViewText(R.id.txtNameMTF, nameSong);


        if (myPlay!!.isPlay()) {

            contentView.setImageViewResource(R.id.imgPlay, R.drawable.ic_pause);

        } else {
            contentView.setImageViewResource(R.id.imgPlay, R.drawable.ic_play);
        }
        val button_intent = Intent("pausePlay")
        val pendingIntent = PendingIntent.getService(context, 123, button_intent, 0)
        contentView.setOnClickPendingIntent(R.id.playPauseNotification, pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            nbuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        notification = nbuilder.notification
        mNotificationManager.notify(123, notification)
        startForeground(123, notification)
        return notification
    }





    fun pause() {
        myPlay!!.pause()
    }

    fun play() {
        myPlay!!.play()
    }

    fun updateNotification() {
        myPlay!!.seekto()
    }

    inner class MyPlay(context: Context) {
        val handler = Handler()
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.nguoitungthuon)

        fun isPlay(): Boolean {
            return mediaPlayer.isPlaying
        }

        fun play() {
            mediaPlayer.start()
        }

        fun pause() {
            mediaPlayer.pause()
        }

        fun seekto() {
            handler.postDelayed(object : Runnable {
                override fun run() {
                    //Do something after 100ms
                    Log.e("AAAAAAAAAAAA ", " " + mediaPlayer.currentPosition / 1000)
                    updateNotification( mediaPlayer.currentPosition / 1000)
                    if (myPlay!!.isPlay())
                    handler.postDelayed(this, 2000)
                }
            }, 1500)
        }
    }
    //cap nhat lai trang thai tren notification
    private fun updateNotification(progress: Int) {
        nbuilder.setOnlyAlertOnce(true)
        contentView.setTextViewText(R.id.txtTime, progress.toString())
        mNotificationManager.notify(123, nbuilder.build())
    }
}



