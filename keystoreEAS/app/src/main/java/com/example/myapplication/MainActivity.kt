package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.text.Charsets.ISO_8859_1


class MainActivity : AppCompatActivity() {
    private lateinit var keyStoreApp: KeystoreApp
    lateinit var pairKeystore:Pair<ByteArray,ByteArray>


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        keyStoreApp= KeystoreApp()
        encrypt!!.setOnClickListener {
            pairKeystore= keyStoreApp.encryptText(txtEnterAlias.text.toString(), txtData.text.toString())
            text.text= Base64.encodeToString(pairKeystore.second, Base64.DEFAULT)
            setBytes(this,pairKeystore.second)

        }
        decrypt!!.setOnClickListener {
            text.text= keyStoreApp.decryptData(txtEnterAlias.text.toString(),getBytes(this),pairKeystore.first) }
        btnListAlias.setOnClickListener { keyStoreApp.getAliases() }
    }


    private val PREF_NAME = "SharedPreferences_Name"
    private val DATA_NAME = "BytesData_Name"

    fun getBytes(ctx: Context): ByteArray? {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val str = prefs.getString(DATA_NAME, null)
        return str?.toByteArray(ISO_8859_1)
    }

    fun setBytes(ctx: Context, bytes: ByteArray?) {
        val prefs: SharedPreferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val e = prefs.edit()
        e.putString(DATA_NAME, String(bytes!!, ISO_8859_1))
        e.commit()
    }


}