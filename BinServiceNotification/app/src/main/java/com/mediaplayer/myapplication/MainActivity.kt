package com.mediaplayer.myapplication

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var myService: MyServiceMediaplayer? = null
    private var intentsss: Intent? = null
    private val connection: ServiceConnection = object : ServiceConnection {
        // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
        override fun onServiceDisconnected(name: ComponentName) {
            Log.e("AAAAAAAAAAA ", "bi loi roi " + name)
        }

        // Phương thức này được hệ thống gọi khi kết nối tới service thành công
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.e("AAAAAAAAAAA ", "bi loi roi ")
            val binder = service as MyServiceMediaplayer.MyBinder
            myService = binder.getService()// lấy đối tượng MyService
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //khoi tao binservice
        myService = MyServiceMediaplayer()

        //khởi tạo service background de nhận sự kiện từ notification
        startService(Intent(this, MyServiceMediaplayer::class.java))
        eventView()
    }

    private fun eventView() {
        btnPause.setOnClickListener {
            myService!!.pause()
        }
        btnPlay.setOnClickListener {
            myService!!.play()
        }
    }


    override fun onStart() {
        super.onStart()
        if (intentsss == null) {
            intentsss = Intent(this, MyServiceMediaplayer::class.java)
            bindService(intentsss, connection, BIND_AUTO_CREATE)
            startService(intentsss)
        }
    }
}