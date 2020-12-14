package com.example.teacherassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Student

class CourseStudentsAdapter(
    var courseStudents:LiveData<List<Student>>,
    var course:Course,
    var deleteCallback: ((s: Student) -> Unit))
    :RecyclerView.Adapter<CourseStudentsAdapter.CourseStudentsHolder>() {
    inner class CourseStudentsHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseStudentsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_coursestudents_list,parent,false)
        return CourseStudentsHolder(view)
    }

    override fun onBindViewHolder(holder: CourseStudentsHolder, position: Int) {
        var textViewStudentName = holder.itemView.findViewById<TextView>(R.id.textViewStudentNameInCourse)
        textViewStudentName.text = courseStudents.value?.get(position)?.firstName.toString()
        var textViewStudentLastName = holder.itemView.findViewById<TextView>(R.id.textViewStudentLastNameInCourse)
        textViewStudentLastName.text = courseStudents.value?.get(position)?.lastName.toString()

        var buttonStudentGradesInCourse = holder.itemView.findViewById<Button>(R.id.buttonStudentGradesInCourse)
        buttonStudentGradesInCourse.setOnClickListener{ view ->
            view.findNavController().navigate(
                R.id.action_courseDetailsFragment_to_gradesStudentInCourseFragment,
                bundleOf(
                    "student" to courseStudents.value?.get(position),
                    "course" to course))
        }

        var buttonDeleteStudentFromCourse = holder.itemView.findViewById<Button>(R.id.buttonDeleteStudentFromCourse)
        buttonDeleteStudentFromCourse.setOnClickListener {
            var student = courseStudents.value?.get(position)
            if (student != null){
                deleteCallback(student)
            }
        }

    }

    override fun getItemCount(): Int = courseStudents.value?.size?:0
}