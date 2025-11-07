package com.example.radienceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var saveButton: Button
    private lateinit var changePasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize views
        nameInput = findViewById(R.id.edit_profile_name)
        emailInput = findViewById(R.id.edit_profile_email)
        passwordInput = findViewById(R.id.edit_profile_password)
        saveButton = findViewById(R.id.save_button)
        changePasswordButton = findViewById(R.id.change_password_button)

        // Example default user info (replace later with real data)
        nameInput.setText("Letsoalo Pauline")
        emailInput.setText("Pauline@example.com")

        // Save button clicked
        saveButton.setOnClickListener {
            val newName = nameInput.text.toString().trim()
            val newEmail = emailInput.text.toString().trim()

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                // You can later save this info in Firebase or SharedPreferences
            }
        }

        // Change password clicked
        changePasswordButton.setOnClickListener {
            val newPassword = passwordInput.text.toString().trim()
            if (newPassword.isEmpty()) {
                Toast.makeText(this, "Enter a new password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show()
                passwordInput.text.clear()
                // Later, link this to FirebaseAuth.updatePassword() if using Firebase
            }
        }
    }
}
