package com.example.todo.ui.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.TodoModel
import com.example.todo.repository.TodoRepository
import com.example.todo.room.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UpdateViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
    }

    fun updateTodo(todo: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTodo(todo)
        }
    }
}
