## Proyecto de Gestión de Tareas

Este proyecto es una aplicación de gestión de tareas desarrollada en Kotlin utilizando Jetpack Compose para la interfaz de usuario y Dagger Hilt para la inyección de dependencias. La aplicación permite a los usuarios añadir, eliminar y actualizar tareas.

### Características

- **Añadir Tareas**: Los usuarios pueden añadir nuevas tareas utilizando un diálogo de entrada.
- **Eliminar Tareas**: Las tareas pueden ser eliminadas mediante una pulsación larga.
- **Actualizar Tareas**: Los usuarios pueden marcar las tareas como completadas o no completadas.

### Estructura del Proyecto

- **UI**: La interfaz de usuario está construida con Jetpack Compose.
- **ViewModel**: La lógica de negocio y la gestión del estado se manejan en `TasksViewModel`.
- **Casos de Uso**: Los casos de uso (`AddTaskUseCase`, `DeleteTaskUseCase`, `UpdateTaskUseCase`) encapsulan la lógica de la aplicación.
- **Repositorio**: `TaskRepository` maneja la interacción con la base de datos.
- **DAO**: `TaskDao` define las operaciones de base de datos.

### Dependencias

- **Jetpack Compose**: Para la construcción de la interfaz de usuario.
- **Dagger Hilt**: Para la inyección de dependencias.
- **Room**: Para la persistencia de datos.


### Uso

1. Pulsa el botón flotante para añadir una nueva tarea.
2. Introduce el texto de la tarea y pulsa "Añadir tarea".
3. Las tareas añadidas aparecerán en la lista.
4. Pulsa largo sobre una tarea para eliminarla.
5. Marca o desmarca una tarea para actualizar su estado.

