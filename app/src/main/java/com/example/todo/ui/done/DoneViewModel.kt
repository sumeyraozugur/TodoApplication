package com.example.todo.ui.done

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.model.TodoModel
import com.example.todo.repository.TodoRepository
import com.example.todo.room.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DoneViewModel(application: Application): AndroidViewModel(application){
    val readAllDone: LiveData<List<TodoModel>>
    private val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllDone = repository.readDoneList
    }

    fun updateTodo(todo: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTodo(todo)
        }
    }
}