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

class StudentCoursesAdapter(
        var studentCourses:LiveData<List<Course>>,
        var student: Student)
    :RecyclerView.Adapter<StudentCoursesAdapter.StudentCoursesHolder>() {
    inner class StudentCoursesHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentCoursesAdapter.StudentCoursesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_studentcourses_list,parent,false)

        return StudentCoursesHolder(view)
    }

    override fun onBindViewHolder(
        holder: StudentCoursesAdapter.StudentCoursesHolder,
        position: Int
    ) {
        var courseName = holder.itemView.findViewById<TextView>(R.id.textViewCourseNameInStudentDetails)
        courseName.text = studentCourses.value?.get(position)?.name.toString()

        var buttonStudentGradesInCourse = holder.itemView.findViewById<Button>(R.id.buttonStudentGradesInCourseStudentDetails)
        buttonStudentGradesInCourse.setOnClickListener{ view ->
            view.findNavController().navigate(
                    R.id.action_studentDetailsFragment_to_gradesStudentInCourseFragment,
                    bundleOf(
                            "student" to student,
                            "course" to studentCourses.value?.get(position)))
        }
    }

    override fun getItemCount(): Int = studentCourses.value?.size?:0


}