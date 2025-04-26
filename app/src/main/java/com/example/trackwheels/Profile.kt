package com.example.trackwheels

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Fetch email and username from SharedPreferences
        val email = sharedPreferences.getString("userEmail", "Not Available")
        val username = sharedPreferences.getString("userUsername", "Not Available")

        // Find the TextViews where the email and username will be displayed
        val emailTextView = findViewById<TextView>(R.id.userNameText)
        val usernameTextView = findViewById<TextView>(R.id.usernameLabel)

        // Set the fetched data to the TextViews
        emailTextView.text = email
        usernameTextView.text = "Username: "+username

        // Set the edge-to-edge behavior for the main layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}