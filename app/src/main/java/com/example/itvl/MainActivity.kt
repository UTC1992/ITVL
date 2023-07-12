package com.example.itvl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email: EditText = findViewById(R.id.txtemail)
        val password: EditText = findViewById(R.id.txtclave)
        val btnLogin: Button = findViewById(R.id.btnacceder)
        btnLogin.setOnClickListener {
            onLogin(email.text.toString(), password.text.toString())
        }
        val btnRegister : Button = findViewById(R.id.btnregistrar)
        btnRegister.setOnClickListener{
            val registrar : Intent = Intent(this, Registrar_activity::class.java)
            startActivity(registrar)
        }

    }

    // Credentials
    // ingedisonvcalvopiniia@gmial.com
    // 12345

    private fun onLogin(email: String, password: String) {
        lifecycleScope.launch {
            try {
                Log.d("Log, email, password", email + password)

                val response = ApiClient.apiService.onLogin(email, password)
                Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()

                Log.d("Log, ", response.toString())
            } catch (e: Exception) {
                Log.d("Log, ", e.toString())
            }
        }
    }
}