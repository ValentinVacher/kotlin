package com.humanbooster.moduledatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var text: String,
    val createdAt: Date = Date(),
    var isDone: Boolean = false,
)