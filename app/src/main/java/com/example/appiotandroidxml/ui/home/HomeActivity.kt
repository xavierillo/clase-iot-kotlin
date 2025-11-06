package com.example.appiotandroidxml.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appiotandroidxml.R
import com.example.appiotandroidxml.data.local.Prefs
import com.example.appiotandroidxml.data.remote.ApiService
import com.example.appiotandroidxml.data.remote.RetrofitClient
import com.example.appiotandroidxml.data.remote.dto.UserDto
import com.example.appiotandroidxml.ui.login.LoginActivity
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    private lateinit var prefs: Prefs

    private lateinit var api: ApiService
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtRole: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // setContentView se utiliza para establecer el diseño de la actividad en un archivo XML.
        prefs = Prefs(this)

        // Conecta el token al interceptor
        RetrofitClient.setTokenProvider { prefs.getToken() }

        // Usa el ApiService listo del RetrofitClient
        api = RetrofitClient.api

        loadProfile()

        txtName = findViewById(R.id.txtHomeName)
        txtEmail = findViewById(R.id.txtHomeEmail)

        findViewById<TextView>(R.id.lblWelcome).text = "¡Bienvenido/a!"
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            prefs.clear()
            startActivity(Intent(this, LoginActivity::class.java))
            //startActivity la funcion que nos permite navegar entre pantallas
            // Intent() se utiliza para crear un objeto Intent que representa una solicitud de cambio de actividad.
            // this se utiliza para obtener una referencia al contexto actual de la actividad.
            // LoginActivity::class.java se utiliza para obtener la clase de la actividad de destino.
            finish()
            // finish() se utiliza para finalizar la actividad actual y volver a la actividad anterior en la pila de actividades.
        }
    }

    private fun loadProfile() {
        lifecycleScope.launch {
            try {
                val response = api.getProfile()
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        txtName.text = it.name
                        txtEmail.text = it.email
                    }
                } else {
                    Toast.makeText(this@HomeActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@HomeActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
