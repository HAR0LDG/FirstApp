package com.haroldg.firstapp.ListaApp

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.haroldg.firstapp.R

class CategoriesViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val txtCategoryName:TextView = view.findViewById(R.id.txtCategoryName)
    private val divider:View = view.findViewById(R.id.divisor)
    private val conItemCategory:CardView = view.findViewById(R.id.containerItemCategory)

    @SuppressLint("SetTextI18n")
    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit){

        val color = if(taskCategory.isSelected){
          R.color.background_card
        }else{
            R.color.background_disabled
        }

        conItemCategory.setCardBackgroundColor(ContextCompat.getColor(conItemCategory.context,color))

        itemView.setOnClickListener{onItemSelected(layoutPosition)}

        when(taskCategory){
            TaskCategory.Business -> {
                txtCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.business_category)
                )
            }
            TaskCategory.Other -> {
                txtCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }
            TaskCategory.Personal -> {
                txtCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }
        }
    }
}