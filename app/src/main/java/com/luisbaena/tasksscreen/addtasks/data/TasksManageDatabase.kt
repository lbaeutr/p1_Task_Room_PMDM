package com.luisbaena.tasksscreen.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Define la base de datos de la aplicación.
 */
@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TasksManageDatabase : RoomDatabase() {
    // DAO
    abstract fun taskDao(): TaskDao
}