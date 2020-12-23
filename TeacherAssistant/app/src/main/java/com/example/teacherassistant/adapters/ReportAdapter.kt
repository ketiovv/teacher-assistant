package com.example.teacherassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Grade

class ReportAdapter(
        var grades: LiveData<List<Grade>>,
        var getStudentCallback:((scId:Int) -> String),
        var getCourseCallback:((scId:Int) -> String))
    : RecyclerView.Adapter<ReportAdapter.GradeHolder>() {
    inner class GradeHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_report, parent, false)

        return GradeHolder(view)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        val grade = holder.itemView.findViewById<TextView>(R.id.textViewGradeValueR)
        grade.text = grades.value?.get(position)?.grade.toString()
        val note = holder.itemView.findViewById<TextView>(R.id.textViewGradeNoteR)
        note.text = grades.value?.get(position)?.note.toString()

        val student = holder.itemView.findViewById<TextView>(R.id.textViewGradeStudentLastName)
        student.text = getStudentCallback(grades.value?.get(position)?.studentCourseId!!)

        val course = holder.itemView.findViewById<TextView>(R.id.textViewGradeCourse)
        course.text = getCourseCallback(grades.value?.get(position)?.studentCourseId!!).toString()

    }

    override fun getItemCount(): Int = grades.value?.size ?: 0
}