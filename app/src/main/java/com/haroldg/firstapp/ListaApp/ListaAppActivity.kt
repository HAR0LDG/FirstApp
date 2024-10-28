package com.haroldg.firstapp.ListaApp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldg.firstapp.R

class ListaAppActivity : AppCompatActivity() {

    private val categories =listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    private lateinit var rvCategory:RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
        initUI()
    }

    private fun initComponent() {
        rvCategory = findViewById(R.id.rvCategories)
    }

    private fun initUI(){
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategory.adapter = categoriesAdapter
    }
}