package com.tuwaiq.todolist_capston1.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepo(context: Context) {
    private val taskDB = TaskDatabase.getAppDataBase(context)!!


    suspend fun getTasks(): List<Task> = withContext(Dispatchers.IO) {
        taskDB.taskDau().getTasks()
    }

    suspend fun insertTask(task: Task) = withContext(Dispatchers.IO) {
        taskDB.taskDau().insert(task)
    }


    suspend fun update(task: Task)= withContext(Dispatchers.IO) {
        taskDB.taskDau().update(task)
    }


    suspend fun delete(task: Task)= withContext(Dispatchers.IO) {
        taskDB.taskDau().delete(task)
    }
}


