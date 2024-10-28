package com.haroldg.firstapp.ListaApp

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.haroldg.firstapp.R

class TasksViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val txtTask:TextView = view.findViewById(R.id.txtTask)
    private val cbCategory:CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task){
        txtTask.text = task.name
    }
}