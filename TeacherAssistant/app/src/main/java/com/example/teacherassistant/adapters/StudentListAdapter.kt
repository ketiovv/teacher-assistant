package com.example.teacherassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.models.Student

class StudentListAdapter(var students:LiveData<List<Student>>) :RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {
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
    }

    override fun getItemCount(): Int = students.value?.size?:0


}