package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.adapters.CourseStudentAddAdapter
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.viewmodels.StudentCourseViewModel
import com.example.teacherassistant.viewmodels.StudentViewModel
import kotlinx.android.synthetic.main.fragment_course_student_add.*

private const val ARG_COURSE = "course"


class CourseStudentAdd : Fragment() {
    private lateinit var course: Course

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var courseStudentAddAdapter:CourseStudentAddAdapter

    private lateinit var studentViewModel:StudentViewModel
    private lateinit var studentCourseViewModel: StudentCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getParcelable(ARG_COURSE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity()).get(StudentCourseViewModel::class.java)

        viewManager = LinearLayoutManager(context)
        courseStudentAddAdapter = CourseStudentAddAdapter(studentViewModel.studentsNotInCourse) { x ->
            studentCourseViewModel.addStudentToCourse(x.id, course.id)
        }

        studentViewModel.studentsNotInCourse.observe(viewLifecycleOwner,{
            courseStudentAddAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_student_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewCourseNameInStudentsAdding.text = course.name

        recyclerViewStudentNotInCourse.apply{
            layoutManager = viewManager
            adapter = courseStudentAddAdapter
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseStudentAdd().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COURSE, course)
                }
            }
    }
}