package com.example.loginfacebooklich

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
lateinit var mCallbackManager:CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCallbackManager = CallbackManager.Factory.create();



    btn_login_facebook.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                txtContent.setText(
                    "User ID: " + loginResult.accessToken.userId + "\n" +
                            "Auth Token: " + loginResult.accessToken.token
                )
            }

            override fun onCancel() {
                txtContent.setText("Login canceled.")
            }

            override fun onError(e: FacebookException) {
                txtContent.setText("Login failed.")
            }
        })

        //  printKeyHash(this)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun printKeyHash(context: Activity): String? {
        val packageInfo: PackageInfo
        var key: String? = null
        try {
            //getting application package name, as defined in manifest
            val packageName = context.applicationContext.packageName

            //Retriving package info
            packageInfo = context.packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )

            Log.e("Package Name=", context.applicationContext.packageName)

            for (signature in packageInfo.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                key = String(Base64.encode(md.digest(), 0))

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key)
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e("Name not found", e1.toString())
        } catch (e: NoSuchAlgorithmException) {
            Log.e("No such an algorithm", e.toString())
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }

        return key
    }
}
