package com.example.appiotandroidxml.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.appiotandroidxml.R
import com.example.appiotandroidxml.data.AuthRepository
import com.example.appiotandroidxml.data.remote.RetrofitClient
import kotlinx.coroutines.*
import com.example.appiotandroidxml.data.local.Prefs
import com.google.android.ads.mediationtestsuite.activities.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var progress: ProgressBar

    private val repo = AuthRepository(RetrofitClient.api)
    private lateinit var prefs: Prefs
    private val uiScope = MainScope() // lifecycle-aware simple scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        prefs = Prefs(this)

        // Si existe token → ir directo a Home
        prefs.getToken()?.let {
            goHome()
            return
        }

        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        progress = findViewById(R.id.progress)

        btnLogin.setOnClickListener { doLogin() }
    }

    private fun doLogin() {
        val email = txtEmail.text.toString().trim()
        val pass  = txtPassword.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty()) {
            toast("Completa email y contraseña")
            return
        }

        setLoading(true)
        uiScope.launch {
            try {
                val res = withContext(Dispatchers.IO) {
                    repo.login(email, pass)
                }
                // Guardar token y navegar
                prefs.saveToken(res.token)
                toast("¡Bienvenido ${res.user.name}!")
                goHome()
            } catch (e: retrofit2.HttpException) {
                when (e.code()) {
                    401 -> toast("Credenciales inválidas")
                    else -> toast("Error del servidor (${e.code()})")
                }
            } catch (e: Exception) {
                toast("Sin conexión o error inesperado")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
        btnLogin.isEnabled = !show
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    private fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // no volver al login
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }
}
