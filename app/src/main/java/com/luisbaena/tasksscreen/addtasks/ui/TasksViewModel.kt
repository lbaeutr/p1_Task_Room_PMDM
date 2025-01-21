package com.luisbaena.tasksscreen.addtasks.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {

    // LiveData para controlar la visibilidad del diálogo
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    // LiveData para almacenar el texto de la tarea
    private val _myTaskText = MutableLiveData<String>()
    val myTaskText: LiveData<String> = _myTaskText

    // Cerrar el diálogo
    fun onDialogClose() {
        _showDialog.value = false
    }

    // Guardar la tarea, cerrar el diálogo y limpiar el texto
    fun onTaskCreated() {
        onDialogClose()
        Log.i("dam2", _myTaskText.value ?: "")
        _myTaskText.value = ""  // Limpiar el campo después de guardar
    }

    // Mostrar el diálogo
    fun onShowDialogClick() {
        _showDialog.value = true
    }

    // Actualizar el texto de la tarea
    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }
}