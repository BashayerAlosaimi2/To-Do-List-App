package com.tuwaiq.todolist_capston1.model

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
    var taskTitle: String,
    var TaskDetails: String="",
    var infoAfterDueDatePass:String="",
    var important: Boolean= false,
    var due_date: String="",
    var taskCompleted: Boolean = false,
    var created_date: String  // = System.currentTimeMillis()

): Parcelable

/*{
    val created_date_formatted: String
        get()= DateFormat.getDateInstance().format(created_date)

    val due_date_formatted: String
        get()= DateFormat.getDateInstance().format(due_date)

}
*/