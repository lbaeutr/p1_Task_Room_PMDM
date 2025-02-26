package com.luisbaena.tasksscreen.addtasks.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


/**
 * Define una interfaz que contiene métodos para interactuar con la tabla TaskEntity en la base de datos.
 */

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>  // Asegurar que devuelve TaskEntity

    @Insert
    suspend fun addTask(task: TaskEntity)  // Aquí debe ser TaskEntity

    @Update
    suspend fun updateTask(task: TaskEntity)  // Aquí también debe ser TaskEntity

    @Delete
    suspend fun deleteTask(task: TaskEntity)  // Aquí también debe ser TaskEntity
}

