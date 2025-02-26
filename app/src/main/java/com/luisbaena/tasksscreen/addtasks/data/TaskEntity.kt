package com.luisbaena.tasksscreen.addtasks.data


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Define una entidad que representa una tarea en la base de datos.
 */

@Entity(tableName = "TaskEntity")
data class TaskEntity(
    @PrimaryKey
    val id: Int,
    val task: String,
    val selected: Boolean = false
)
