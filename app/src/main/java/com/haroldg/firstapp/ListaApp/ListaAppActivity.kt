package com.haroldg.firstapp.ListaApp

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.haroldg.firstapp.ListaApp.TaskCategory.*
import com.haroldg.firstapp.R

class ListaAppActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Other
    )

    private val tasks = mutableListOf(
        Task(name = "Business Task", Business),
        Task(name = "Personal Task", Personal),
        Task(name = "Other Task", Other)
    )

    private lateinit var rvCategory: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fabButton: FloatingActionButton

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
        initListeners()
    }

    private fun initListeners() {
        fabButton.setOnClickListener {
            showDialog()
        }
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAgregarTask)
        val edTask: EditText = dialog.findViewById(R.id.edTextTask)
        val rgCategory: RadioGroup = dialog.findViewById(R.id.rgCategories)

        var toast: Toast? = null // Variable para guardar el Toast actual

        btnAddTask.setOnClickListener {
            val taskText = edTask.text.toString().trim()

            if (taskText.isEmpty()) {
                // Cancela el Toast anterior si está visible
                toast?.cancel()
                // Muestra un nuevo Toast
                toast = Toast.makeText(this, getString(R.string.msgToast), Toast.LENGTH_SHORT)
                toast?.show()
            } else {
                val selectedId = rgCategory.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategory.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.labelRadioBusiness) -> Business
                    getString(R.string.labelRadioPersonal) -> Personal
                    else -> Other
                }
                tasks.add(Task(taskText, currentCategory))
                updateTasks()
                dialog.dismiss()
            }
        }
        dialog.setOnDismissListener {
            toast?.cancel() // Cancela el Toast al cerrar el diálogo
        }
        dialog.show()
    }

    private fun initComponent() {
        rvCategory = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabButton = findViewById(R.id.fabButton)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategory.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) { onItemSelected(it) }
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun updateTasks(){
        tasksAdapter.notifyDataSetChanged()
    }

    private fun onItemSelected(position:Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }
}