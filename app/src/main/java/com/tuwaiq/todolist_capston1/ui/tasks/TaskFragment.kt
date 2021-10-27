package com.tuwaiq.todolist_capston1.ui.tasks

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
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

//action_taskFragment_to_taskDetailsFragment


        addBtn.setOnClickListener{
            findNavController().navigate(R.id.action_taskFragment_to_addFragment)

        }

        viewModel = ViewModelProvider(this).get(taskViewModel::class.java)
        recyclerView.layoutManager= LinearLayoutManager(this.context)


        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
            val isLandscape = view.findViewById<FrameLayout>(R.id.flDetails) != null
            recyclerView.adapter = TaskRecycleViewAdapter(it,isLandscape)

        })

        val mainVM = ViewModelProvider(this).get(taskViewModel::class.java)

        //..
   /*     val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    return false
                }

                override suspend fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    mainVM.delete(TaskRecycleViewAdapter.getTask(viewHolder.adapterPosition))
                    Toast.makeText(context,"Task Deleted"),Toast.LENGTH_SHORT).show()
                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rvNote)*/

     /*   recyclerView = view.findViewById(R.id.recycleV)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = UserRVAdapter(viewModel.getAllUsers(),isLandscape)*/
    }
    }

