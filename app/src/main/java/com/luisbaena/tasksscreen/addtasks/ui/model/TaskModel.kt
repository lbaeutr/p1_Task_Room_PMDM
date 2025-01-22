package com.luisbaena.tasksscreen.addtasks.ui.model




//// Nuestro modelo de datos
//// El valor del id se calcula automáticamente con el tiempo actual en milisegundos.
//// El valor de selected (casilla de verificación) se establece en falso por defecto.
//
//data class TaskModel(
//    val id: Long = System.currentTimeMillis(),
//    val task: String,
//    var selected: Boolean = false
//)


// Nuestro modelo de datos...
data class TaskModel(
    // El método hashCode() nos crea un código único basado en la hora actual en milisegundos.
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)