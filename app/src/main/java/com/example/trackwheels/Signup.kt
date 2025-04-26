package com.example.trackwheels

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.io.File

class Signup : AppCompatActivity() {

    private lateinit var usernameInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var policySwitch: Switch
    private lateinit var signupButton: Button

    private val folderName = "TrackWheelUsers" // Folder to store all users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize views
        usernameInput = findViewById(R.id.textinputlayouttext1)
        emailInput = findViewById(R.id.textinputlayouttext2)
        passwordInput = findViewById(R.id.textinputlayouttext3)
        policySwitch = findViewById(R.id.switch1)
        signupButton = findViewById(R.id.login_btn)

        // Initially disable the SignUp button
        signupButton.isEnabled = false
        signupButton.alpha = 0.5f

        // Enable button only if switch is checked
        policySwitch.setOnCheckedChangeListener { _, isChecked ->
            signupButton.isEnabled = isChecked
            signupButton.alpha = if (isChecked) 1.0f else 0.5f
        }

        // Sign up button logic
        signupButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showToast("Please fill all fields")
                return@setOnClickListener
            }

            // Create a folder to store user data if it doesn't exist
            val userFolder = File(filesDir, folderName)
            if (!userFolder.exists()) {
                userFolder.mkdir() // Create folder if it doesn't exist
            }

            // Check if the user already exists by checking for their username
            val userFile = File(userFolder, "$username.txt")
            if (userFile.exists()) {
                showToast("Username already exists. Try a different one.")
            } else {
                // Write the user data to a file
                userFile.writeText("Username: $username\nEmail: $email\nPassword: $password")
                showToast("Signup successful!")

                // After successful signup, navigate to Login screen
                val intent = Intent(this, Loginscreen::class.java)
                startActivity(intent)
                finish() // Close the signup activity
            }
        }
    }

    // Function to clear the input fields after signup
    private fun clearFields() {
        usernameInput.text?.clear()
        emailInput.text?.clear()
        passwordInput.text?.clear()
    }

    // Function to display toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
