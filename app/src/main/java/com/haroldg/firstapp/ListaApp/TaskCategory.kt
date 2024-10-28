package com.haroldg.firstapp.ListaApp

sealed class TaskCategory {
    data object Personal:TaskCategory()
    data object Business:TaskCategory()
    data object Other:TaskCategory()
}