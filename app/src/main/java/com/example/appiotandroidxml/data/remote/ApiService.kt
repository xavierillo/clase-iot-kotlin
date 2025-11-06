package com.example.appiotandroidxml.data.remote

import com.example.appiotandroidxml.data.remote.dto.LoginRequest
import com.example.appiotandroidxml.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
}
