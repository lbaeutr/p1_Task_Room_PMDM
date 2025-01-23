package com.luisbaena.tasksscreen.addtasks.data.di




import android.content.Context
import androidx.room.Room
import com.luisbaena.tasksscreen.addtasks.data.TaskDao
import com.luisbaena.tasksscreen.addtasks.data.TaskRepository
import com.luisbaena.tasksscreen.addtasks.data.TasksManageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    // Provee la instancia del DAO a partir de la base de datos creada
    @Provides
    fun provideTaskDao(tasksManageDatabase: TasksManageDatabase): TaskDao {
        return tasksManageDatabase.taskDao()
    }

    // Provee la instancia de la base de datos como Singleton
    @Provides
    @Singleton
    fun provideTasksManageDatabase(@ApplicationContext appContext: Context): TasksManageDatabase {
        return Room.databaseBuilder(
            appContext,
            TasksManageDatabase::class.java,
            "TaskDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }

}