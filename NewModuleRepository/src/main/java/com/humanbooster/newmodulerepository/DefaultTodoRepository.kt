package com.humanbooster.newmodulerepository

import android.content.Context
import androidx.room.Room
import com.humanbooster.moduledatabase.TodoDatabase
import com.humanbooster.moduledatabase.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DefaultTodoRepository(context: Context) : TodoRepository {

    private val db = Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, TodoDatabase.NAME).build()

    override fun observeAll(): Flow<List<Todo>> {
        return db.todoDao().observeAll()
    }

    override suspend fun insert(todo: Todo) {
        withContext(Dispatchers.IO) {
            db.todoDao().insert(todo)
        }
    }

    override suspend fun switchChecked(todo: Todo) {
        withContext(Dispatchers.IO){
            db.todoDao().switchChecked(todo)
        }
    }

    override suspend fun update(todo: Todo) {
        withContext(Dispatchers.IO){
            db.todoDao().update(todo)
        }
    }

    override suspend fun delete(todo: Todo) {
        withContext(Dispatchers.IO){
            db.todoDao().delete(todo)
        }
    }
}