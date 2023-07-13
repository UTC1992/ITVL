package com.example.itvl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Registrar_activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterRecyclerView
    private var userType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        recyclerView = findViewById(R.id.lstobtenerItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dni: EditText = findViewById(R.id.txtcedula_regi)
        val name: EditText = findViewById(R.id.txtapellidos)
        val email: EditText = findViewById(R.id.txtcorreo)

        val btnRegister: Button = findViewById(R.id.btn_registar2)
        btnRegister.setOnClickListener {
            onRegister(dni.text.toString(), name.text.toString(), email.text.toString(), userType)
        }

        // get subjects
        onGetSubjects()

    }

    private fun onRegister(dni: String, name: String, email: String, code: Int) {
        lifecycleScope.launch {
            try {
                Log.d("Log, Data Register", dni + email + name + code)

                val response = ApiClient.apiService.onRegister(dni, name, email, code)
                Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()

                Log.d("Log, response", response.toString())
            } catch (e: Exception) {
                Log.d("Log, ", e.toString())
            }
        }
    }

    private fun onGetSubjects() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.onGetSubjects()

                Log.d("Log, response", response.asignaturas.size.toString())

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, response.asignaturas[0].denominacion, Toast.LENGTH_SHORT).show()
                    adapter = AdapterRecyclerView(response.asignaturas)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    adapter.setOnItemClickListener(object : AdapterRecyclerView.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val selectedItem = response.asignaturas[position]
                            userType = selectedItem.id
                            Log.d("Log, Item", selectedItem.id.toString())
                        }
                    })
                }

            } catch (e: Exception) {
                Log.d("Log, Error", e.toString())
                withContext(Dispatchers.Main) {
                    // logica para actualizar UI o interfaz de usuario
                }
            }
        }
    }
}