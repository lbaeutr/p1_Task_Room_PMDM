package com.luisbaena.tasksscreen.addtasks.domain



import com.luisbaena.tasksscreen.addtasks.data.TaskRepository
import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel
import javax.inject.Inject

/**
 * Caso de uso para eliminar una tarea
 */

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskId: TaskModel) {
        taskRepository.deleteTask(taskId)
    }
}