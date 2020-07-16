package com.example.myapplication

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class KeystoreApp {
    private var keyStore: KeyStore
    private val TRANSFORMATION = "AES/GCM/NoPadding"
    private val ANDROID_KEY_STORE = "AndroidKeyStore"
    lateinit var encryption: ByteArray
    lateinit var iv: ByteArray
    init {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun decryptData(alias: String, encryptedData: ByteArray?, encryptionIv: ByteArray?): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(128, encryptionIv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKeyssa(alias), spec)
//        return String(cipher.doFinal(encryptedData), "UTF-8")
        return String(cipher.doFinal(encryptedData))
    }
    private fun getSecretKeyssa(alias: String): SecretKey {
        return (keyStore!!.getEntry(alias, null) as KeyStore.SecretKeyEntry).secretKey
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun encryptText(alias: String, textToEncrypt: String): Pair<ByteArray, ByteArray> {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKeyss(alias))
        iv = cipher.iv
      return  Pair<ByteArray,ByteArray>(iv,cipher.doFinal(textToEncrypt.toByteArray(charset("UTF-8"))))
       // return cipher.doFinal(textToEncrypt.toByteArray(charset("UTF-8"))).also { encryption = it }
    }
    @RequiresApi(Build.VERSION_CODES.M)
     private fun getSecretKeyss(alias: String): SecretKey {
        val keyGenerator = KeyGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        keyGenerator.init(
            KeyGenParameterSpec.Builder(alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build())
        return keyGenerator.generateKey()
    }
     fun getAliases() {
        var aliasesString = ""
        val aliases = keyStore.aliases()
        while (aliases.hasMoreElements()) {
            aliasesString += "${aliases.nextElement()}, "
        }
    }
}