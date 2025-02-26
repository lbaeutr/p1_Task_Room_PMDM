package com.luisbaena.tasksscreen.addtasks.data

import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio que interactúa con la base de datos para obtener, añadir, actualizar y eliminar tareas.
 */
@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map { TaskModel(it.id, it.task, it.selected) }
    }

    suspend fun addTask(taskModel: TaskModel) {
        val entity = TaskEntity(taskModel.id, taskModel.task, taskModel.selected)
        taskDao.addTask(entity)
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }

    suspend fun updateTask(taskModel: TaskModel) {
        val entity = TaskEntity(taskModel.id, taskModel.task, taskModel.selected)
        taskDao.updateTask(entity)

    }


}


