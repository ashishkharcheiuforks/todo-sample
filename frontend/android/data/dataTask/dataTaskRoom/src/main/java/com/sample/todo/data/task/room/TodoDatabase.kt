package com.sample.todo.data.task.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.todo.data.task.room.entity.TaskEntity
import com.sample.todo.data.task.room.entity.TaskFtsEntity

@Database(
    entities = [
        TaskEntity::class,
        TaskFtsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}