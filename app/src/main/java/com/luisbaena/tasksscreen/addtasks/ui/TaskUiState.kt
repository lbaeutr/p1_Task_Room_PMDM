package com.luisbaena.tasksscreen.addtasks.ui

import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel

/**
 * Estados de la pantalla
 */
sealed interface TaskUiState {

    // Si no recibe datos, usamos un object
    object Loading : TaskUiState

    // Estado de error con una excepción
    data class Error(val throwable: Throwable) : TaskUiState

    // Estado de éxito con una lista de tareas
    data class Success(val tasks: List<TaskModel>) : TaskUiState

}