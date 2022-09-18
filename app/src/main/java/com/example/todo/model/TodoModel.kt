package com.example.todo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todo_table")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    val id:Int,
    @ColumnInfo(name = "todo_task")
    val task:String,
    @ColumnInfo(name = "todo_fav")
    var isFav:Boolean =false
):Parcelable