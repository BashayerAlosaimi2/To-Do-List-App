package com.tuwaiq.todolist_capston1.ui.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.todolist_capston1.data.Task

import com.tuwaiq.todolist_capston1.data.TaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class taskViewModel(context : Application) : AndroidViewModel(context) {
    private val repo = TaskRepo(context)


    // to deal with Live Data < Mutable to be able to change
    fun getTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
// post يعبي
            tasks.postValue(repo.getTasks())
        }
        return tasks
    }
    // we dont need to observe  here

    fun inserTask(task: Task) =
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertTask(task)
        }

    suspend fun update(task: Task) = withContext(Dispatchers.IO) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(task)
        }
    }

        suspend fun delete(task: Task) = withContext(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {
                repo.delete(task)
            }
        }



}