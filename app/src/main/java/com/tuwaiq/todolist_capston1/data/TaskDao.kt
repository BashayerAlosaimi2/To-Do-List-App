package com.tuwaiq.todolist_capston1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {


    // We don't need suspend ere because it happened in the Flow
    // Flow is asynconans stream of data
    @Query("SELECT * FROM task_table ORDER BY created_date ASC")
    fun getTasks():List<Task>

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}