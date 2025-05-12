package com.example.budgetnestprototype.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetnestprototype.DashboardActivity
import com.example.budgetnestprototype.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivityActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (email.isEmpty()) {
                etEmail.error = "Enter your email"
                return@setOnClickListener
            } else if (!isValidEmail(email)) {
                etEmail.error = "Invalid email format"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Enter your Password"
                return@setOnClickListener
            }

            // Check user in Room DB
            lifecycleScope.launch {
                val repo = AuthRepository.getInstance(this@LoginActivityActivity)
                val user = repo.getUserByEmail(email)
                if (user != null && user.password == password) {
                    startActivity(Intent(this@LoginActivityActivity, DashboardActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivityActivity, "Invalid credentials or user does not exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}