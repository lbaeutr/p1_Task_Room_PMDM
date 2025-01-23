package com.luisbaena.tasksscreen.addtasks.domain

import com.luisbaena.tasksscreen.addtasks.data.TaskRepository
import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Caso de uso para recuperar las tareas
 *
 * Para acceder a la capa de datos necesitamos el repositorio, ya que es nuestra
 * puerta de entrada a los datos. Gracias a Dagger Hilt lo inyectamos en el constructor.
 */
class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}