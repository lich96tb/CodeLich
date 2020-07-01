package com.example.rulesfirebase.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.rulesfirebase.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

class Login : AppCompatActivity() {
    private val FCM_API = "https://fcm.googleapis.com/fcm/send"
    private val serverKey =
        "key=" + "AAAAzG2b0A4:APA91bEw92-OfbdxBLPB45EJ395BZdYDf7HOnW9HmX-6oZkg_zNVLF6SEetuGQ6zVpWQpYWoKw3Zdxon4Abx0eRa0wHCrjMJ5hjpMkrYElepxU9AAT4ZVx90Pe7RAY4Wby6WosJ71auh"
    private val contentType = "application/json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/Enter_your_topic_name")

        button2.setOnClickListener {
            //gui cho 1 nguoi
            val topic ="cEYN-a5apKc:APA91bHA3Oi849DPms-WVKHdibm048P-EfV4q66TOHAKSYazRqmp50DPOLVZwgceWcN2UYNBt7HouyaGW-aP6iQ8NAxYCGCtNIVVMftz0v55L8H5WpDLtj08sa0NzWBVh6lAD_5rjlK_"
           //gui cho nhieu nguoi
              //   val topic ="/topics/Enter_your_topic_name" //topic has to match what the receiver subscribed to
            val notification = JSONObject()
            val notifcationBody = JSONObject()

            try {
                notifcationBody.put("title", "Enter_title")
                notifcationBody.put("message", "xin chao nhe")   //Enter your notification message
                notification.put("to", topic)
                notification.put("data", notifcationBody)
                Log.e("TAG", "try")
            } catch (e: JSONException) {
                Log.e("TAG", "onCreate: " + e.message)
            }
            sendNotification(notification)
        }
        button3.setOnClickListener {
            // [START retrieve_current_token]
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("TAG error", "", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token
                    Log.w("TAG tocken",  token.toString())
                    // Log and toast
                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
        }

    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(this.applicationContext)
    }

    private fun sendNotification(notification: JSONObject) {
        Log.e("TAG", "sendNotification")
        val jsonObjectRequest = object : JsonObjectRequest(FCM_API, notification,
            Response.Listener<JSONObject> { response ->
                Log.e("AAAAAAAAAAAAAA ", response.toString())
            },
            Response.ErrorListener {
                Toast.makeText(this, "Request error", Toast.LENGTH_LONG).show()
                Log.i("TAG", "onErrorResponse: Didn't work")
            }) {

            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = serverKey
                params["Content-Type"] = contentType
                return params
            }
        }
        Log.e("AAAAAAAAAAAAA:  ",jsonObjectRequest.body.toString())
        requestQueue.add(jsonObjectRequest)
    }
}
