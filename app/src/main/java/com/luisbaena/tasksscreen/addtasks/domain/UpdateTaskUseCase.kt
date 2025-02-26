package com.luisbaena.tasksscreen.addtasks.domain



import com.luisbaena.tasksscreen.addtasks.data.TaskRepository
import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel
import javax.inject.Inject

/**
 * Caso de uso para actualizar una tarea
 */
class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.updateTask(taskModel)
    }
}