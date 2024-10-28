package com.haroldg.firstapp.Menu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.haroldg.firstapp.HomeActivity
import com.haroldg.firstapp.IMC_Calculator.IMCActivity
import com.haroldg.firstapp.R
import com.haroldg.firstapp.ListaApp.ListaAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSaludar = findViewById<Button>(R.id.btnSaludar)
        val btnIMC = findViewById<Button>(R.id.btnIMC)
        val btnLista = findViewById<Button>(R.id.btnListaAPP)

        btnSaludar.setOnClickListener{
            navigateToSaludoApp()
        }

        btnIMC.setOnClickListener{
            navigateToIMC()
        }

        btnLista.setOnClickListener{
            navigateToListaApp()
        }
    }

    private fun navigateToListaApp() {
        val intent = Intent(this,ListaAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMC() {
        val intent = Intent(this,IMCActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludoApp(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}