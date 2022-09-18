package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.model.TodoModel
import com.example.todo.room.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val readAllData: LiveData<List<TodoModel>> = todoDao.readAllData()
    val readDoneList: LiveData<List<TodoModel>> = todoDao.getDoneList()


    suspend fun addTodo(todo: TodoModel) {
        todoDao.addTodo(todo)
    }
    suspend fun updateTodo(todo: TodoModel) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: TodoModel) {
        todoDao.deleteTodo(todo)
    }



}
