package com.tuwaiq.todolist_capston1.data

//import java.text.DateFormat
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import androidx.room.Entity
import androidx.room.PrimaryKey
@Parcelize
@Entity(tableName ="task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskTitle: String,
    val TaskDetails: String,
    val important: Boolean= false,
    val due_date: String,
    val taskCompleted: Boolean = false,
    val created_date: String// = System.currentTimeMillis()

): Parcelable/*{
    val created_date_formatted: String
        get()= DateFormat.getDateInstance().format(created_date)

    val due_date_formatted: String
        get()= DateFormat.getDateInstance().format(due_date)

}
*/