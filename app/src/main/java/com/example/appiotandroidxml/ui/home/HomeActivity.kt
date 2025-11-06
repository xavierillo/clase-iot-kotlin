package com.example.appiotandroidxml.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appiotandroidxml.R
import com.example.appiotandroidxml.data.local.Prefs
import com.example.appiotandroidxml.ui.login.LoginActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        prefs = Prefs(this)

        findViewById<TextView>(R.id.lblWelcome).text = "¡Bienvenido/a!"
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            prefs.clear()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
