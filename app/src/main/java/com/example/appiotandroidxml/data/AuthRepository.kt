package com.example.appiotandroidxml.data

import com.example.appiotandroidxml.data.remote.ApiService
import com.example.appiotandroidxml.data.remote.dto.LoginRequest
import com.example.appiotandroidxml.data.remote.dto.LoginResponse

class AuthRepository(private val api: ApiService) {
    suspend fun login(email: String, password: String): LoginResponse =
        api.login(LoginRequest(email, password))
}