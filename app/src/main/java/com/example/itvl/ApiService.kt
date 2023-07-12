package com.example.itvl

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("lab_acceso.php")
    suspend fun onLogin(@Query("correo") email: String, @Query("clave") password: String): Any

    @GET("lab_grabar_user.php")
    suspend fun onRegister(
        @Query("cedula") dni: String,
        @Query("nombres") name: String,
        @Query("correo") email: String,
        @Query("cod_asig") code: String,
        ): Any
}