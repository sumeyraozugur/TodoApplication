package com.example.todo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.model.TodoModel

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao

    companion object{
        private var INSTANCE: TodoDatabase?= null

        fun getDatabase(context: Context):TodoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE =instance
                return instance
            }
        }


    }


}