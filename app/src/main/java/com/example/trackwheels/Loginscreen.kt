package com.example.trackwheels

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class Loginscreen : AppCompatActivity() {

    // Declare SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginscreen)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val login_btn = findViewById<Button>(R.id.login_btn)
        val textinputlayouttext1 = findViewById<EditText>(R.id.textinputlayouttext1) // Email
        val textinputlayouttext2 = findViewById<EditText>(R.id.textinputlayouttext2) // Password

        login_btn.setOnClickListener {
            val inputEmail = textinputlayouttext1.text.toString().trim()
            val inputPassword = textinputlayouttext2.text.toString().trim()

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Verify the credentials
                val username = verifyUserCredentials(inputEmail, inputPassword)
                if (username != null) {
                    // Save email and username to SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("userEmail", inputEmail)
                    editor.putString("userUsername", username)
                    editor.apply()

                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to Home Screen
                    val intent = Intent(this, HomeMainScreen::class.java)
                    startActivity(intent)
                    finish() // Optional, close the login activity
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Function to verify the user credentials and return the username if valid
    private fun verifyUserCredentials(email: String, password: String): String? {
        // Get the directory where user data is stored
        val userFolder = File(filesDir, "TrackWheelUsers")

        if (!userFolder.exists()) return null

        // List all user folders
        val userFiles = userFolder.listFiles() ?: return null

        // Iterate through each folder to check if any file matches the credentials
        for (userFile in userFiles) {
            if (userFile.isFile && userFile.name.endsWith(".txt")) {
                // Read the content of the user file
                val fileContent = userFile.readText()

                // Extract username, email, and password from the file content
                val storedUsername = fileContent.lines().firstOrNull { it.startsWith("Username:") }?.substringAfter("Username:")?.trim()
                val storedEmail = fileContent.lines().firstOrNull { it.startsWith("Email:") }?.substringAfter("Email:")?.trim()
                val storedPassword = fileContent.lines().firstOrNull { it.startsWith("Password:") }?.substringAfter("Password:")?.trim()

                // Validate the credentials
                if (storedEmail == email && storedPassword == password) {
                    return storedUsername // Return the username if credentials match
                }
            }
        }
        return null // Return null if no match is found
    }
}