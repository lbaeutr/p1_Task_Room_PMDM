package com.luisbaena.tasksscreen.addtasks.data


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TasksManageDatabase : RoomDatabase() {
    // DAO
    abstract fun taskDao(): TaskDao
}