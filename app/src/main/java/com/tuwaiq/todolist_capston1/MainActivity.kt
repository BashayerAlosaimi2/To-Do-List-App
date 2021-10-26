package com.tuwaiq.todolist_capston1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todolist_capston1.data.Task
import com.tuwaiq.todolist_capston1.data.TaskDatabase
import com.tuwaiq.todolist_capston1.ui.tasks.TaskFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*
        val mainFragment = TaskFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, mainFragment)
            commit()
        }*/
    }
}