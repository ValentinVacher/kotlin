package com.humanbooster.moduledatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.humanbooster.moduledatabase.entity.DateTimeConverter
import com.humanbooster.moduledatabase.entity.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object{
        const val NAME = "todos"
    }
}