package com.haroldg.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    val boton = findViewById<AppCompatButton>(R.id.boton)
    val campo_Texto = findViewById<AppCompatEditText>(R.id.textfield)


    boton.setOnClickListener{
        val name = campo_Texto.text.toString()
        if(name.isNotEmpty()){
            val intent = Intent(this, SaludoActivity::class.java)
            intent.putExtra("EXTRA-NAME", name)
            startActivity(intent)
            Log.i("haroldg","Btn pulsado $name")
            }
        }
    }
}