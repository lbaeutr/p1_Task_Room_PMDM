package com.luisbaena.tasksscreen.addtasks.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


/**
 * Define una interfaz que contiene métodos para interactuar con la tabla TaskEntity en la base de datos.
 */

//ESTO ES DEL ENUNCIADO Y LA SOLUCION VIENE MAS ABAJO
//@Dao
//interface TaskDao {
//    @Query("SELECT * from TaskEntity")
//    fun getTasks(): Flow<List<TaskEntity>>
//
//    @Insert
//    suspend fun addTask(item: TaskEntity)
//}


@Dao
interface TaskDao {
    @Query("SELECT * from TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(item: TaskEntity)}


