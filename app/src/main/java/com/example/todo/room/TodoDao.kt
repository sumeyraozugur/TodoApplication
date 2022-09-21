package com.example.todo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.model.TodoModel

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: TodoModel)

    @Query("SELECT * FROM todo_table ORDER BY todo_id ASC")
    fun readAllData(): LiveData<List<TodoModel>>

    @Update
    suspend fun updateTodo(todo: TodoModel)

    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    @Query("SELECT * FROM todo_table WHERE todo_fav = 1")
    fun getDoneList(): LiveData<List<TodoModel>>

    @Query("SELECT * FROM todo_table WHERE todo_task  like '%' || :searchQuery || '%'  ") //WHERE firstName  like '%' || :searchQuery || lastName like '%'
    suspend fun searchDatabase(searchQuery: String): List<TodoModel>

}