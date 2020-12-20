package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.viewmodels.CourseViewModel
import kotlinx.android.synthetic.main.fragment_course_edit.*

private const val ARG_COURSE = "course"

class CourseEditFragment : Fragment() {
    private lateinit var course : Course

    private lateinit var courseViewModel: CourseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getParcelable(ARG_COURSE)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        courseViewModel = ViewModelProvider(requireActivity())
            .get(CourseViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewCourseInEditActual.text = course.name
        editTextCourseNameAfterEdit.setText(course.name)
        buttonSaveEditName.setOnClickListener {
            courseViewModel.updateCourse(course,editTextCourseNameAfterEdit.text.toString())
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(course: Course) =
            CourseEditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COURSE, course)
                }
            }
    }
}