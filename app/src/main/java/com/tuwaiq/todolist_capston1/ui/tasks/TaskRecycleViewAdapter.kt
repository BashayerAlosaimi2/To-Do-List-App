package com.tuwaiq.todolist_capston1.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todolist_capston1.R
import com.tuwaiq.todolist_capston1.data.Task

class TaskRecycleViewAdapter (private var taskList:List<Task>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        //parent is the recycle layout which will get injected with views (items)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return CustomAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val task = taskList[position]
        holder.tv_TaskName.text = "${task.taskTitle}"
        holder.tv_TaskDetails.text = "${task.TaskDetails} "
        holder.tv_DueDate.text = "${task.due_date_formatted}"
        holder.tv_CreationDate.text = "${task.created_date_formatted}"

        holder.itemView.setOnClickListener { view ->
            val action = TaskFragmentDirections.actionTaskFragmentToAddFragment()
            view.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>){
        taskList =task
        notifyDataSetChanged()

    }

}

class CustomAdapter {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // item view here is from the on create function where we created in the view
        val tv_TaskName: TextView = itemView.findViewById(R.id.tvTaskName)
        val tv_TaskDetails: TextView = itemView.findViewById(R.id.tvTaskDetails)
        val tv_DueDate: TextView = itemView.findViewById(R.id.tvDueDate)
        val tv_CreationDate: TextView = itemView.findViewById(R.id.tvCreationDate)


        init {
            itemView.setOnClickListener(this)

        }


        override fun onClick(view: View?) {
            Toast.makeText(itemView.context, "${tv_TaskName.text} cliced", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
