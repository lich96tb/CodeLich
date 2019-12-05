package com.example.fingerprint

import android.Manifest
import android.annotation.TargetApi
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.view.View
import android.widget.TextView

import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_fingerprint.*

import java.io.IOException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

@RequiresApi(api = Build.VERSION_CODES.M)
open class FingerprintActivity : AppCompatActivity() {
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ad)

        var fingerprint=Fingerprint()
//        fingerprint.generateKey()
//
//        if (fingerprint.cipherInit()) {
//            val cryptoObject = FingerprintManager.CryptoObject(fingerprint.cipher!!)
//            val helper = FingerprintHandler(this)
//            val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
//            helper.startAuth(fingerprintManager, cryptoObject)
//        }


        // Initializing both Android Keyguard Manager and Fingerprint Manager
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager


        // Check whether the device has a Fingerprint sensor.
        if (!fingerprintManager.isHardwareDetected) {
            /**
             * An error message will be displayed if the device does not contain the fingerprint hardware.
             * However if you plan to implement a default authentication method,
             * you can redirect the user to a default authentication activity from here.
             * Example:
             * Intent intent = new Intent(this, DefaultAuthenticationActivity.class);
             * startActivity(intent);
             */
            errorText!!.text = "Your Device does not have a Fingerprint Sensor"
        } else {
            // Checks whether fingerprint permission is set on manifest
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.USE_FINGERPRINT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                errorText!!.text = "Fingerprint authentication permission not enabled"
            } else {
                //kiểm tra xem trong máy đã có vân tay chưa
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    errorText!!.text = "Register at least one fingerprint in Settings"
                } else {
                    // Checks whether lock screen security is enabled or not
                    if (!keyguardManager.isKeyguardSecure) {
                        errorText!!.text = "Lock screen security not enabled in Settings"
                    } else {
                        fingerprint.generateKey()

                        if (fingerprint.cipherInit()) {
                            val cryptoObject = FingerprintManager.CryptoObject(fingerprint.cipher!!)
                            val helper = FingerprintHandler(this)
                            helper.startAuth(fingerprintManager, cryptoObject)
                        }
                    }
                }
            }
        }
    }


}