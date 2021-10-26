package com.tuwaiq.todolist_capston1.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuwaiq.todolist_capston1.R
import com.tuwaiq.todolist_capston1.data.Task
import com.tuwaiq.todolist_capston1.data.TaskDatabase

class TaskFragment : Fragment(R.layout.fragment_task){

    private lateinit var addBtn: FloatingActionButton
    private lateinit var viewModel: taskViewModel
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // inflater layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view_tasks)
        addBtn= view.findViewById(R.id.fab_add_task)
        addBtn.setOnClickListener{
            findNavController().navigate(R.id.action_taskFragment_to_addFragment)

        }


        viewModel = ViewModelProvider(this).get(taskViewModel::class.java)


        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
        recyclerView.adapter = TaskRecycleViewAdapter(it)
        })

    }



    }

