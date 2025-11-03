package com.example.appiotandroidxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.appiotandroidxml.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    private var loading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        // Instala el splash lo antes posible
        val splash = installSplashScreen()

        super.onCreate(savedInstanceState)

        // (Opcional) mantenerlo brevemente mientras cargas algo ligero
        // splash.setKeepOnScreenCondition { false }
        // Mantenemos el splash mientras loading sea true
        splash.setKeepOnScreenCondition { loading }

        // Simulamos una tarea en background (p. ej., validación de login)
        lifecycleScope.launch {
            delay(1500) // Espera 1.5 segundos o espera real de una consulta
            loading = false // Cuando termina, el splash desaparece automáticamente
        }

        enableEdgeToEdge()
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
       // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}