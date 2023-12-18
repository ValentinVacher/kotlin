package com.humanbooster.todolist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.humanbooster.moduledatabase.entity.Todo
import com.humanbooster.todolist.BaseApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val todoRepository = BaseApplication.getInstance().todoRepository

    private val _state = MutableStateFlow(HomeScreenState())

    val state: StateFlow<HomeScreenState>
        get() = _state

    init {
        viewModelScope.launch {
            todoRepository.observeAll().collect{
                _state.value = _state.value.copy(todos = it)
            }
        }
    }

    fun onInsertTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.insert(todo)
        }
    }

    fun onChecked(todo: Todo){
        viewModelScope.launch {
            todoRepository.switchChecked(todo)
        }
    }

    fun onUpdate(todo: Todo){
        viewModelScope.launch {
            todoRepository.update(todo)
        }
    }

    fun onDeleteTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.delete(todo)
        }
    }
}