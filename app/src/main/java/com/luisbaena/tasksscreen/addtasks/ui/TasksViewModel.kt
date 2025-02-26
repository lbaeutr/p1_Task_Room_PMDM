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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
//    private val addTaskUseCase: AddTaskUseCase,  // Inyectar caso de uso para añadir tareas
//    getTasksUseCase: GetTasksUseCase  // Inyectar caso de uso para obtener tareas

    private val addTaskUseCase: AddTaskUseCase,  // Inyectar caso de uso para añadir tareas
    private val getTasksUseCase: GetTasksUseCase,  // Inyectar caso de uso para obtener tareas
    private val deleteTaskUseCase: DeleteTaskUseCase,  // Inyectar caso de uso para eliminar tareas
    private val updateTaskUseCase: UpdateTaskUseCase  // Inyectar caso de uso para actualizar tareas
) : ViewModel() {

    //esta variable es introducida en el punto 7 del ejercicio
    val uiState: StateFlow<TaskUiState> = getTasksUseCase()
        .map(::Success)
        .catch { Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TaskUiState.Loading
        )


//    // Lista mutable para almacenar las tareas con Compose
//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks: List<TaskModel> = _tasks

    // LiveData para controlar la visibilidad del diálogo
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

//    // LiveData para almacenar el texto de la tarea
//    private val _myTaskText = MutableLiveData<String>()
//    val myTaskText: LiveData<String> = _myTaskText

    //actualizacion de la parte de arriba
    private val _myTaskText = MutableStateFlow("")
    val myTaskText: StateFlow<String> = _myTaskText


//    // Cerrar el diálogo
//    fun onDialogClose() {
//        _showDialog.value = false
//    }

    // Cerrar el diálogo (sin cambios necesarios)
    fun onDialogClose() {
        _showDialog.value = false
    }

//    // Guardar la tarea, cerrar el diálogo y limpiar el texto
//    fun onTaskCreated() {
//        onDialogClose()
//      //  Log.i("dam2", _myTaskText.value ?: "")
//        _tasks.add(TaskModel(task = _myTaskText.value ?: ""))
//        _myTaskText.value = ""  // Limpiar el campo después de guardar
//    }

    //actualizacion de la parte de arriba
    // ACTUALIZACIÓN 3: Modificar la función para insertar tareas en Room usando AddTaskUseCase
    fun onTaskCreated() {
        onDialogClose()
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = _myTaskText.value))  // Guardamos la tarea en Room usando el caso de uso
        }
        _myTaskText.value = ""  // Limpiar el campo después de guardar la tarea
    }

//    // Mostrar el diálogo
//    fun onShowDialogClick() {
//        _showDialog.value = true
//    }

    //actualizacion de la parte de arriba
    // Mostrar el diálogo (sin cambios necesarios)
    fun onShowDialogClick() {
      //  _myTaskText.value = ""
        _showDialog.value = true // Mostrar el diálogo no estaba en el código original
    }


//    // Actualizar el texto de la tarea
//    fun onTaskTextChanged(taskText: String) {
//        _myTaskText.value = taskText
//    }

    //actualizacion de la parte de arriba
    // ACTUALIZACIÓN 4: Cambiar LiveData a MutableStateFlow para manejar cambios en el campo de texto
    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }



//    fun deleteTask(task: TaskModel) {
//        // Implementación para eliminar la tarea
//    }
//
//    fun updateTask(task: TaskModel) {
//        // Implementación para actualizar la tarea
//    }


    fun onItemRemove(task: TaskModel) {
        viewModelScope.launch {
            //deleteTaskUseCase(task.id)
            deleteTaskUseCase(task)

            // taskRepository.delete(taskModel)
        }
    }

    fun onCheckBoxSelected(task: TaskModel) {
        viewModelScope.launch {
//            val updatedTask = task.copy(selected = !task.selected)
//            updateTaskUseCase(updatedTask)
           // taskRepository.update(taskModel.copy(selected = !taskModel.selected))  // Invertir el estado y actualizar en Room
            updateTaskUseCase(task.copy(selected = !task.selected))  // Invertir estado de selección


        }
    }







/*

ESTAS FUNCIONES YA NO SERAN NECESARIAS DEBIDO QUE UISTATE SE ENCARGA DE MOSTRAR LOS DATOS


    fun onItemRemove(taskModel: TaskModel) {
        // No podemos usar directamente _tasks.remove(taskModel) porque no es
        // posible por el uso de let con copy para modificar el checkbox...
        // Para hacerlo correctamente, debemos previamente buscar la tarea en la
        // lista por el id y después eliminarla
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
//Si se modifica directamente _tasks[index].selected = true no se        recompone el item en el LazyColumn
//Esto nos ha pasado ya en el proyecto BlackJack... ¿¿os acordáis?? :-(
//Y es que la vista no se entera que debe recomponerse, aunque realmente                si se ha modificado el valor en el item
//Para solucionarlo y que se recomponga sin problemas en la vista, lo        hacemos con un let...
//El método let toma como parámetro el objeto y devuelve el resultado de        la expresión lambda
//En nuestro caso, el objeto que recibe let es de tipo TaskModel, que        está en _tasks[index]
//(sería el it de la exprexión lambda)
//El método copy realiza una copia del objeto, pero modificando la        propiedad selected a lo contrario
//El truco está en que no se modifica solo la propiedad selected de        tasks[index],
//sino que se vuelve a reasignar para que la vista vea que se ha        actualizado un item y se recomponga.
        _tasks[index] = _tasks[index].let { it.copy(selected = !it.selected) }
    }


 */

}