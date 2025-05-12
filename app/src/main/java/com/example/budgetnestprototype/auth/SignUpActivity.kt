package com.example.budgetnestprototype.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetnestprototype.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<TextInputEditText>(R.id.etConfirmPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvHaveAccount = findViewById<TextView>(R.id.tvHaveAccount)

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (email.isEmpty()) {
                etEmail.error = "Enter your email"
                return@setOnClickListener
            } else if (!isValidEmail(email)) {
                etEmail.error = "Invalid Email format."
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Enter your password"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                etConfirmPassword.error = "Confirm your password"
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                etConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Save new user to Room DB
            lifecycleScope.launch {
                val repo = AuthRepository.getInstance(this@SignUpActivity)
                val existingUser = repo.getUserByEmail(email)

                if (existingUser != null) {
                    Toast.makeText(this@SignUpActivity, "User already exists", Toast.LENGTH_SHORT).show()
                } else {
                    repo.insertUser(User(email = email, password = password))
                    Toast.makeText(this@SignUpActivity, "Account created successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, LoginActivityActivity::class.java))
                    finish()
                }
            }
        }

        tvHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivityActivity::class.java))
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}