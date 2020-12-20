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

class StudentListAdapter(
    var students:LiveData<List<Student>>,
    var deleteCallback: ((s: Student) -> Unit))
    :RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {
    inner class StudentHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_student_list,parent,false)

        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var textViewName = holder.itemView.findViewById<TextView>(R.id.textViewStudentName)
        var textViewLastName = holder.itemView.findViewById<TextView>(R.id.textViewStudentLastName)

        textViewName.text = students.value?.get(position)?.firstName
        textViewLastName.text = students.value?.get(position)?.lastName

        var buttonOpenStudent= holder.itemView.findViewById<Button>(R.id.buttonStudentCourses)
        buttonOpenStudent.setOnClickListener{ view ->
            view.findNavController().navigate(
                R.id.action_studentListFragment_to_studentDetailsFragment,
                bundleOf("student" to students.value?.get(position))
            )
        }

        var buttonEditStudent = holder.itemView.findViewById<Button>(R.id.buttonEditStudent)
        buttonEditStudent.setOnClickListener { view ->
            view.findNavController().navigate(
                R.id.action_studentListFragment_to_studentEditFragment,
                bundleOf("student" to students.value?.get(position))
            )
        }


        var buttonDeleteStudent= holder.itemView.findViewById<Button>(R.id.buttonDeleteStudent)
        buttonDeleteStudent.setOnClickListener{
            var student =  students.value?.get(position)
            if (student != null) deleteCallback(student)
        }

    }

    override fun getItemCount(): Int = students.value?.size?:0


}