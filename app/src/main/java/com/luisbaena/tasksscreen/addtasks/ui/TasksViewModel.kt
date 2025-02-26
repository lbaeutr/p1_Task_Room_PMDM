package com.luisbaena.tasksscreen.addtasks.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisbaena.tasksscreen.addtasks.domain.AddTaskUseCase
import com.luisbaena.tasksscreen.addtasks.domain.DeleteTaskUseCase
import com.luisbaena.tasksscreen.addtasks.domain.GetTasksUseCase
import com.luisbaena.tasksscreen.addtasks.domain.UpdateTaskUseCase
import com.luisbaena.tasksscreen.addtasks.ui.TaskUiState.Success
import com.luisbaena.tasksscreen.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// Clase que representa el estado de la UI de la pantalla de tareas (TasksFragment)
@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
     getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTasksUseCase()
        .map(::Success)
        .catch { Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TaskUiState.Loading
        )

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _myTaskText = MutableLiveData<String>()
    val myTaskText: LiveData<String> = _myTaskText

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated() {
        onDialogClose()
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = _myTaskText.value ?: ""))
        }
        _myTaskText.value = ""
    }

    fun onShowDialogClick() {
        _showDialog.value = true // Mostrar el diálogo no estaba en el código original
    }

    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }

    fun onItemRemove(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task)

        }
    }

    fun onCheckBoxSelected(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(selected = !task.selected))  // Invertir estado de selección


        }
    }









}