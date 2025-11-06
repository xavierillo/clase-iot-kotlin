package com.example.appiotandroidxml.data.local

import android.content.Context
import android.content.SharedPreferences

class Prefs(ctx: Context) {
    private val sp: SharedPreferences =
        ctx.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) = sp.edit().putString("jwt", token).apply()
    fun getToken(): String? = sp.getString("jwt", null)
    fun clear() = sp.edit().clear().apply()
}
