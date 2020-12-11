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
import com.example.teacherassistant.models.Course

class CourseListAdapter(var courses: LiveData<List<Course>>):RecyclerView.Adapter<CourseListAdapter.CourseHolder>() {
    inner class CourseHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_course_list,parent,false)
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        var textViewName = holder.itemView.findViewById<TextView>(R.id.textViewCourseName)
        textViewName.text = courses.value?.get(position)?.name.toString()

        var buttonOpenCourse = holder.itemView.findViewById<Button>(R.id.buttonOpenCourse)
        buttonOpenCourse.setOnClickListener{ view ->
            view.findNavController().navigate(
                R.id.action_courseListFragment_to_courseDetailsFragment,
                bundleOf("course" to courses.value?.get(position)))
        }
    }

    override fun getItemCount(): Int = courses.value?.size?:0

}