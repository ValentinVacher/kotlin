package com.humanbooster.todolist

import android.app.Application
import com.humanbooster.newmodulerepository.DefaultTodoRepository
import com.humanbooster.newmodulerepository.TodoRepository

class BaseApplication : Application() {

    val todoRepository: TodoRepository by lazy {
        DefaultTodoRepository(this)
    }

    companion object {
        private var _instance: BaseApplication? = null
        fun getInstance(): BaseApplication = _instance!!
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}