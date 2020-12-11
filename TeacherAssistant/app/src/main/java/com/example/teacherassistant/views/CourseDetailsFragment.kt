package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teacherassistant.R
import com.example.teacherassistant.models.Course
import kotlinx.android.synthetic.main.fragment_course_details.*

private const val ARG_COURSE = "course"


class CourseDetailsFragment : Fragment() {
    private lateinit var course: Course

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewCourseNameLabel.text = course.name
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