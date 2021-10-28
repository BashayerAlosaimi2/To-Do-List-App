package com.tuwaiq.todolist_capston1.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.tuwaiq.todolist_capston1.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDau(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getAppDataBase(context: Context): TaskDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, TaskDatabase::class.java, "Task-database"
                ).build()

            }
            return INSTANCE
        }
    }

}
