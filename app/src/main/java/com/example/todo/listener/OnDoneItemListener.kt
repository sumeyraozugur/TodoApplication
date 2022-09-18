package com.example.todo.listener

import com.example.todo.model.TodoModel

interface OnDoneItemListener {
    fun star(todo: TodoModel)

}