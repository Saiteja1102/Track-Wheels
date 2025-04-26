package com.example.trackwheels

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeMainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val clickhere_cardetails = findViewById<TextView>(R.id.clickhere_cardetails)
        clickhere_cardetails.setOnClickListener {
            val intent = Intent(this,CarRangeDetails::class.java)
            startActivity(intent)
        }

        val card_view_car1 = findViewById<LinearLayout>(R.id.card_view_car1)
        card_view_car1.setOnClickListener{
            val intent = Intent(this,CarScreen::class.java)
            startActivity(intent)
        }

        val gmap = findViewById<ImageView>(R.id.gmap)
        gmap.setOnClickListener {
            val intent = Intent(this, GMapActivity::class.java)
            startActivity(intent)
        }

        val showrent = findViewById<LinearLayout>(R.id.showrent)
        showrent.setOnClickListener {
            val intent = Intent(this,RentPage::class.java)
            startActivity(intent)
        }

        val profile = findViewById<ImageView>(R.id.profile_id)
        profile.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }


    }
}