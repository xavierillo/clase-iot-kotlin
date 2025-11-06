package com.example.appiotandroidxml.data.remote

import com.example.appiotandroidxml.data.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Cambia por tu dominio
    private const val BASE_URL = "http://ec2-98-95-27-212.compute-1.amazonaws.com/"
        //"https://TU_API/"

    // Token provider configurable
    @Volatile private var tokenProvider: (() -> String?) = { null }
    fun setTokenProvider(provider: () -> String?) { tokenProvider = provider }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor { tokenProvider() }) // usa SIEMPRE el token más reciente
            // (Opcional) timeouts y logging en debug
            // .connectTimeout(15, TimeUnit.SECONDS)
            // .readTimeout(20, TimeUnit.SECONDS)
            // .apply { if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply { level = BODY }) }
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
