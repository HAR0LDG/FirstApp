package com.haroldg.firstapp.ListaApp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.haroldg.firstapp.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtTask: TextView = view.findViewById(R.id.txtTask)
    private val cbCategory: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {

        txtTask.text = task.name
        cbCategory.isChecked = task.isSelected

        updateStrikeThrough(task.isSelected)

        val color = when (task.category) {
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Other -> R.color.other_category
            TaskCategory.Personal -> R.color.personal_category
        }

        cbCategory.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbCategory.context, color)
        )

        cbCategory.setOnCheckedChangeListener { _, isChecked ->
            task.isSelected = isChecked
            updateStrikeThrough(isChecked)
        }
    }

    private fun updateStrikeThrough(isSelected: Boolean) {
        if (isSelected) {
            txtTask.paintFlags = txtTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            txtTask.paintFlags = txtTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
