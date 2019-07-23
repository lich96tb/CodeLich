package com.example.socketio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private var mSocket: Socket? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSocket = IO.socket("http://192.168.0.100:3000/")
        mSocket?.connect()
//        //gui du lieu di
//        mSocket?.emit("client-send-data", "lap trinh android")
//        //nhan du lieu tu server
        mSocket?.on("server-send-data", onRetrieveData)
        mSocket?.on("server-send-list-user", onRetrieveListUser)
        imgAddUser.setOnClickListener {
            if (!edtContent.text.isEmpty())
                mSocket?.emit("clientRegisterUser", edtContent.text.toString().trim())
        }
        imgSen.setOnClickListener {
            if (!edtContent.text.isEmpty())
                mSocket?.emit("clientSenChat", edtContent.text.toString().trim())
        }
        Toast.makeText(this@MainActivity, "dang ky thanh cong", Toast.LENGTH_LONG).show()

    }


    private var onRetrieveData = Emitter.Listener {
        var objectJson = it[0] as JSONObject
        var ketQua = objectJson.getBoolean("ketqua")

        if (ketQua) {

        } else {

        }

        Log.e("kdskjdj ", " $ketQua")
    }

    private var onRetrieveListUser = Emitter.Listener {
        var objectJson = it[0] as JSONObject
        var listUser = objectJson.getJSONArray("listUser") as JSONArray
        for (i in 0 until listUser.length()){
            Log.e("AAAAdd "," "+listUser.getString(i))
        }
    }


}
