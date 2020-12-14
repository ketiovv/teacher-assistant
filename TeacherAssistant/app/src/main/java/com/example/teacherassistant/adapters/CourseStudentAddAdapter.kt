package com.example.teacherassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Student

class CourseStudentAddAdapter(
    var students:LiveData<List<Student>>,
    var addCallback:((s:Student) -> Unit))
    :RecyclerView.Adapter<CourseStudentAddAdapter.StudentHolder>() {
    inner class StudentHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_course_student_add,parent,false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val textViewNameOfStudentToAdd = holder.itemView.findViewById<TextView>(R.id.textViewNameOfStudentToAdd)
        textViewNameOfStudentToAdd.text = students.value?.get(position)?.firstName
        val textViewLastNameOfStudentToAdd = holder.itemView.findViewById<TextView>(R.id.textViewLastNameOfStudentToAdd)
        textViewLastNameOfStudentToAdd.text = students.value?.get(position)?.lastName
        val buttonAddThisStudentToCourse = holder.itemView.findViewById<Button>(R.id.buttonAddThisStudentToCourse)
        buttonAddThisStudentToCourse.setOnClickListener {
            val student = students.value?.get(position)
            if (student != null) addCallback(student)
        }
    }

    override fun getItemCount(): Int = students.value?.size?:0
}