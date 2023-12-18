package com.humanbooster.newmodulerepository

import com.humanbooster.moduledatabase.entity.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun observeAll(): Flow<List<Todo>>
    suspend fun insert(todo: Todo)

    suspend fun switchChecked(todo: Todo)

    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)
}