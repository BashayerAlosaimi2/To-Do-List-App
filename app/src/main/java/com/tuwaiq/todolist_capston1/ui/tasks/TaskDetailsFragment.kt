package com.tuwaiq.todolist_capston1.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tuwaiq.todolist_capston1.R
import com.tuwaiq.todolist_capston1.ui.tasks.modelView.taskViewModel
import kotlinx.android.synthetic.main.fragment_task_details.view.*

class TaskDetailsFragment : Fragment() {

    private lateinit var viewModel: taskViewModel
    private lateinit var updateBtn: Button
    private lateinit var deleteBtn: Button
    private val args by navArgs<TaskDetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // inflater layout for this fragment
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(taskViewModel::class.java)

        view.tvTaskName.setText("Task: ${args.curruntTaskKey.taskTitle}")
        view.tvTaskDetails.setText("Details: ${args.curruntTaskKey.TaskDetails}")
        view.tvDueDate.setText("Due Date: ${args.curruntTaskKey.due_date}")
        view.tvCreationDate.setText("Creation Date: ${args.curruntTaskKey.created_date}")

        updateBtn = view.findViewById(R.id.updateButton)
        deleteBtn = view.findViewById(R.id.deleteButton)
        //val taskArg = args.curruntTaskKey

        updateBtn.setOnClickListener {
            val action =
                TaskDetailsFragmentDirections.actionTaskDetailsFragmentToUpdateFragment(args.curruntTaskKey)

            findNavController().navigate(action)
        }

        deleteBtn.setOnClickListener {

            viewModel.delete(args.curruntTaskKey)
            findNavController().popBackStack()

        }
    }
}