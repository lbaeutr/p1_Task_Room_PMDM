package com.luisbaena.tasksscreen.addtasks.data


import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "TaskEntity") // ESTO ES DEL ENUNCIADO Y LA SOLUCION VIENE MAS ABAJO
//@Entity // PRUEBAS
//data class TaskEntity(
//    @PrimaryKey val id: Int,
//    val task: String,
//    val selected: Boolean = false
//)

@Entity(tableName = "TaskEntity")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val task: String,
    val selected: Boolean = false
)
