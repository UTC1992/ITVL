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
import kotlinx.coroutines.launch


class Registrar_activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterRecyclerView
    private var dataList: ArrayList<ItemList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        dataList.add(ItemList("Admin"))
        dataList.add(ItemList("User"))
        dataList.add(ItemList("Manager"))

        recyclerView = findViewById(R.id.lstobtenerItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterRecyclerView(dataList)
        recyclerView.adapter = adapter

        var userType = ""

        adapter.setOnItemClickListener(object : AdapterRecyclerView.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedItem = dataList[position]
                userType = selectedItem.title.toString()
                Log.d("Log, Item", selectedItem.title)
            }
        })

        val dni: EditText = findViewById(R.id.txtcedula_regi)
        val name: EditText = findViewById(R.id.txtapellidos)
        val email: EditText = findViewById(R.id.txtcorreo)

        val btnRegister: Button = findViewById(R.id.btn_registar2)
        btnRegister.setOnClickListener {
            onRegister(dni.text.toString(), name.text.toString(), email.text.toString(), userType.toString() )
        }

    }

    private fun onRegister(dni: String, name: String, email: String, code: String) {
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
}