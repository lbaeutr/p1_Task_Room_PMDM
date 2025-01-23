package com.luisbaena.tasksscreen.addtasks.data

import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    // Mapeo de datos desde TaskEntity (data) hacia TaskModel (UI/Dominio)
    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map { TaskModel(it.id, it.task, it.selected) }
    }
}