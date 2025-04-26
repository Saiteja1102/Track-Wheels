package com.example.trackwheels

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginSignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val alreadyhaveaccount = findViewById<TextView>(R.id.alreadyhaveaccount)
        alreadyhaveaccount.setOnClickListener{
            val intent = Intent(this, Loginscreen::class.java)
            startActivity(intent)
        }

        val myCustomButton1 = findViewById<LinearLayout>(R.id.myCustomButton1)
        myCustomButton1.setOnClickListener{
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }
}