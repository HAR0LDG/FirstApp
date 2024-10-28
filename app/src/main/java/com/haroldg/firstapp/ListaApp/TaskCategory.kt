package com.haroldg.firstapp.ListaApp

sealed class TaskCategory {
    object Personal:TaskCategory()
    object Business:TaskCategory()
    object Other:TaskCategory()
}