package com.example.itvl

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("lab_acceso.php")
    suspend fun onLogin(@Query("correo") email: String, @Query("clave") password: String): Any
}