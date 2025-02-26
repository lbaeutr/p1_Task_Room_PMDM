package com.luisbaena.tasksscreen.addtasks.ui.model



/**
 * Modelo de datos de una tarea.
 */
data class TaskModel(
    // El método hashCode() nos crea un código único basado en la hora actual en milisegundos.
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)