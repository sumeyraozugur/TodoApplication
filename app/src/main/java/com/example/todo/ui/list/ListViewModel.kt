package com.example.todo.ui.list

import android.app.Application
import android.util.Log
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
    private val repository: TodoRepository
    var tempList = MutableLiveData<List<TodoModel>>()


    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
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

    fun searchDatabase(searchQuery:String) {
        viewModelScope.launch(Dispatchers.IO) {
            tempList.postValue(repository.searchDatabase(searchQuery))
            Log.v("ViewModel", repository.searchDatabase(searchQuery).toString())
        }
    }
}