package com.example.abc.binservice.a

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.mediaplayer.myapplication.MyService

class MyService : Service() {
    private var myPlay: MyPlay? = null
    private var binder: IBinder? = null
    private var mNotificationManager: NotificationManager? = null
    private var nbuilder: Notification.Builder? = null
    private var notification: Notification? = null
    override fun onCreate() {
        super.onCreate()
        Log.e("aaaaaaaaaaaaaaa ","2222222222")
        binder = MyBinder()
        myPlay = MyPlay(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        showNotification1()
        return START_STICKY
    }

    private fun showNotification1() {
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nbuilder = Notification.Builder(this)
        nbuilder!!.setSmallIcon(R.drawable.bg_use_ads)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance)
            nbuilder!!.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }
        // mNotificationManager.notify(0, nbuilder.build());
        notification = nbuilder!!.notification
        startForeground(OPENVPN_STATUS, notification)
    }

    inner class MyBinder : Binder() {
        val services=this@MyService
    }

    fun play() {

        myPlay!!.play()
    }

    fun pause() {
        myPlay!!.pause()
    }

    fun seekto() {
        myPlay!!.seekto()
    }

    fun hidenNotification() {
        stopForeground(false)
        mNotificationManager!!.notify(OPENVPN_STATUS, notification)
        //hủy hẳn notificcation
        //mNotificationManager.cancel(OPENVPN_STATUS);
    }

    internal inner class MyPlay(context: Context) {
        private val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.nguoitungthuon)
        fun play() {
            Log.e("AAAAAAAAAAASd ","ksjkdfjds")
            mediaPlayer.start()
        }

        fun pause() {
            mediaPlayer.pause()
        }

        fun seekto() {
            mediaPlayer.seekTo(120000)
        }

        //them bai hat
        init {
            mediaPlayer.isLooping = true
        }
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "ljdk"
        private const val OPENVPN_STATUS = 1
        const val START_SERVICE = "de.blinkt.openvpn.START_SERVICE"
    }
}