package com.example.appiotandroidxml.data.remote

import com.example.appiotandroidxml.data.remote.dto.LoginRequest
import com.example.appiotandroidxml.data.remote.dto.LoginResponse
import com.example.appiotandroidxml.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("profile")
    suspend fun getProfile(): Response<UserDto>
}
