package com.humanbooster.todolist.home

import com.humanbooster.moduledatabase.entity.Todo

data class HomeScreenState (
    val todos: List<Todo> = emptyList()
)