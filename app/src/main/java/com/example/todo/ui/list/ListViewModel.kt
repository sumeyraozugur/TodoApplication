package com.example.todo.ui.list

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

class ListViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<TodoModel>>
    val readAllDone: LiveData<List<TodoModel>>
    private var _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean>
        get() = _isFav

    private val repository: TodoRepository
    var tempList = MutableLiveData<List<TodoModel>>()

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
        readAllDone = repository.readDoneList

    }

    fun deleteTodo(user: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTodo(user)
        }
    }
    fun updateTodo(todo: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTodo(todo)
        }
    }




}