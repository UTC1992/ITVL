package com.example.itvl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_registro : Button = findViewById(R.id.btnregistrar)
        btn_registro.setOnClickListener{
            val registrar : Intent = Intent(this, Registrar_activity::class.java)
            startActivity(registrar)
        }




    }
}