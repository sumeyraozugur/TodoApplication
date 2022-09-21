package com.example.todo.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.model.TodoModel
import com.example.todo.repository.TodoRepository
import com.example.todo.room.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<TodoModel>>

    private val repository: TodoRepository


    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todo: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)

        }
    }
}