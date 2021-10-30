package com.tuwaiq.todolist_capston1.ui.tasks

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuwaiq.todolist_capston1.R
import com.tuwaiq.todolist_capston1.model.Task
import com.tuwaiq.todolist_capston1.ui.tasks.modelView.taskViewModel
import kotlinx.android.synthetic.main.fragment_task_details.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class updateFragment : Fragment() {


    private val args by navArgs<updateFragmentArgs>()

    private lateinit var taskName: EditText
    private lateinit var taskDetails: EditText
    private lateinit var moreDetails: EditText
    private lateinit var dueDate: TextView
    private lateinit var imagDate: ImageView
    private lateinit var isimportant: CheckBox
    private lateinit var btnDone: FloatingActionButton
    private lateinit var date: String
    private lateinit var clear: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // inflater layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskName = view.findViewById(R.id.et_update_title)
        taskDetails = view.findViewById(R.id.et_update_description)
        moreDetails = view.findViewById(R.id.et_more_description)
        imagDate = view.findViewById(R.id.image_due_date_up)

        isimportant = view.findViewById(R.id.update_check_box_important)
        dueDate = view.findViewById(R.id.tv_update_DueDate)
        btnDone = view.findViewById(R.id.fab_save_task_up)
        clear = view.findViewById(R.id.clear_data_up)

        val mainVM = ViewModelProvider(this).get(taskViewModel::class.java)

        view.et_update_title.setText(args.curruntTaskForUpdate.taskTitle)
        view.et_update_description.setText(args.curruntTaskForUpdate.TaskDetails)
        view.tv_update_DueDate.setText(args.curruntTaskForUpdate.due_date)

        if (args.curruntTaskForUpdate.important)
            view.update_check_box_important.isChecked = true
        else
            view.update_check_box_important.isChecked = false


        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-M-d")
        val currentDate = current.format(formatter)


        if (dueDate.text.isNotEmpty()) {
            val dueDateTime = LocalDate.parse(dueDate.text, formatter)
            if (dueDateTime.isBefore(LocalDate.now())) {
                taskName.isEnabled = false
                taskDetails.isEnabled = false
                imagDate.isEnabled = false
                isimportant.isEnabled = false
                Toast.makeText(context, "Due date is past", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        btnDone.setOnClickListener {
            if (taskName.text.isNotEmpty()) {
                args.curruntTaskForUpdate.taskTitle = taskName.text.toString()
                args.curruntTaskForUpdate.TaskDetails = taskDetails.text.toString()
                args.curruntTaskForUpdate.infoAfterDueDatePass = moreDetails.text.toString()
                args.curruntTaskForUpdate.important = isimportant.isChecked
                args.curruntTaskForUpdate.due_date = dueDate.text.toString()
                args.curruntTaskForUpdate.created_date = currentDate

                mainVM.update(args.curruntTaskForUpdate)
                Toast.makeText(context, "Task updated successfully", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_updateFragment_to_taskFragment)

            } else {
                Toast.makeText(context, "Please enter the task", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //============
        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        imagDate.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(view.context, { view, y, m, d ->
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


    }
}
