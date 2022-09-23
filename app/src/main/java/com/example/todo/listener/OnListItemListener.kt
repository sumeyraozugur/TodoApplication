package com.example.todo.listener

import com.example.todo.model.TodoModel

interface OnListItemListener {
    fun delete(todo:TodoModel)

}