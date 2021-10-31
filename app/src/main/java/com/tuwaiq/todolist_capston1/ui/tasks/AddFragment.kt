package com.tuwaiq.todolist_capston1.ui.tasks

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuwaiq.todolist_capston1.R
import com.tuwaiq.todolist_capston1.model.Task
import com.tuwaiq.todolist_capston1.ui.tasks.modelView.taskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class AddFragment : Fragment() {
    private lateinit var taskName: EditText
    private lateinit var taskDetails: EditText
    private lateinit var dueDate: TextView
    private lateinit var imagDate: ImageView
    private lateinit var isimportant: CheckBox

    //private lateinit var creationDate: TextView
    private lateinit var btnDone: FloatingActionButton
    private lateinit var date: String
    private lateinit var clear: Button


    val current = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatted = current.format(formatter)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainVM = ViewModelProvider(this).get(taskViewModel::class.java)

        taskName = view.findViewById(R.id.edit_text_task_title)
        taskDetails = view.findViewById(R.id.edit_text_task_description)
        imagDate = view.findViewById(R.id.image_due_date)

        isimportant = view.findViewById(R.id.check_box_important)
        dueDate = view.findViewById(R.id.tvDueDate)
        //  creationDate = view.findViewById(R.id.tvCreationDate)
        btnDone = view.findViewById(R.id.fab_save_task)
        clear = view.findViewById(R.id.clear_data)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        imagDate.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(view.context, { _, y, m, d ->
                    date = "$y-${m + 1}-$d"

                    dueDate.setText(date)

                }, year, month, day)
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
            datePickerDialog.show()

        }
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            // alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                taskName.text = null
                taskDetails.text = null
                dueDate.text = null
                isimportant.isChecked = false

            }
            alert.setNegativeButton(R.string.no) { dialog, which ->
                dialog.cancel()
            }
            alert.setNeutralButton(R.string.cancel) { dialog, which ->
                dialog.cancel()
            }
            alert.show()
        }

        //5. Send Information
        btnDone.setOnClickListener {
            if (taskName.text.isNotEmpty()) {
                val task = Task(
                    taskTitle = taskName.text.toString(),
                    TaskDetails = taskDetails.text.toString(),
                    important = isimportant.isChecked,
                    due_date = dueDate.text.toString(),
                    created_date = formatted
                )
                Toast.makeText(context, "Task is added successfully", Toast.LENGTH_SHORT)
                    .show()
                mainVM.inserTask(task)
                findNavController().popBackStack()
                // findNavController().navigate(R.id.action_addFragment_to_taskFragment)
            } else {
                Toast.makeText(context, "Please enter the task", android.widget.Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
