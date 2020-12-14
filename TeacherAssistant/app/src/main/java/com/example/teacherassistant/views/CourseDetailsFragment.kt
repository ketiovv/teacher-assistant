package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.adapters.CourseStudentsAdapter
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.viewmodels.StudentCourseViewModel
import com.example.teacherassistant.viewmodels.StudentViewModel
import kotlinx.android.synthetic.main.fragment_course_details.*

private const val ARG_COURSE = "course"


class CourseDetailsFragment : Fragment() {
    private lateinit var course: Course

    private lateinit var viewManager:RecyclerView.LayoutManager
    private lateinit var courseStudentsAdapter: CourseStudentsAdapter

    private lateinit var studentViewModel:StudentViewModel
    private lateinit var studentCourseViewModel:StudentCourseViewModel

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
        studentViewModel = ViewModelProvider(requireActivity())
            .get(StudentViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity())
            .get(StudentCourseViewModel::class.java)

        viewManager = LinearLayoutManager(context)
        courseStudentsAdapter = CourseStudentsAdapter(
            studentViewModel.students,
            course
        ) { x ->
            studentCourseViewModel.deleteStudentFromCourse(x, course)
        }

        studentViewModel.setCourseId(course.id)
        studentViewModel.students.observe(viewLifecycleOwner,{
            courseStudentsAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewCourseNameLabel.text = course.name

        recyclerViewCourseStudents.apply{
            layoutManager = viewManager
            adapter = courseStudentsAdapter
        }

        buttonAddStudentToCourse.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_courseDetailsFragment_to_courseStudentAdd,
                bundleOf("course" to course))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(course: Course) =
            CourseDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COURSE, course)
                }
            }
    }
}