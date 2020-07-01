package com.example.rulesfirebase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = "AAAAAAAAAAAAAA"
    private var mDatabase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        eventView()

    }

    private fun initView() {
        auth = FirebaseAuth.getInstance()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().reference.child("UserTest");
    }

    private fun eventView() {
        button.setOnClickListener {
            if (!edtGmail.text.trim().isNullOrEmpty() && !edtPassWord.text.trim().isNullOrEmpty()) {
                val student =
                    Student(edtGmail.text.toString().trim(), edtPassWord.text.toString().trim())
                mDatabase!!.child(student.name!!).setValue(student)
                    .addOnSuccessListener {
                        Toast.makeText(this@MainActivity, "thanh cong", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Log.i("MainActivity: ", it.message.toString())
                        Toast.makeText(
                            this@MainActivity,
                            "Loi roi: " + it.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }

        }
        btnRegister.setOnClickListener {

            auth.createUserWithEmailAndPassword(
                edtGmail.text.trim().toString(),
                edtPassWord.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Log.d(TAG, "success $taskId")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
        }
        btnLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(
                edtGmail.text.trim().toString(),
                edtPassWord.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser
                        Log.d(TAG, "signInWithEmail:success" + user!!.uid)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        btnSignOut.setOnClickListener {
            auth.signOut()
        }
    }


}
