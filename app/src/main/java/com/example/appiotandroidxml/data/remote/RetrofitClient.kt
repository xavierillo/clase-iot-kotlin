package com.example.appiotandroidxml.data.remote

import com.example.appiotandroidxml.data.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Cambia por tu dominio
    private const val BASE_URL = "http://ec2-98-95-27-212.compute-1.amazonaws.com"
        //"https://TU_API/"

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor {
                // Aquí obtenemos el token desde SharedPreferences
                // (Solo si lo guardaste en Prefs al hacer login)
                // Cambia el contexto según tu implementación
                null  // puedes remplazarlo más adelante por Prefs(context).getToken()
            })
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
