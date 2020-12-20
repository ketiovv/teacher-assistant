package com.example.teacherassistant.views

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.entities.StudentCourse
import com.example.teacherassistant.viewmodels.GradeViewModel
import com.example.teacherassistant.viewmodels.StudentCourseViewModel
import kotlinx.android.synthetic.main.fragment_grade_add.*

private const val ARG_STUDENT = "student"
private const val ARG_COURSE = "course"

class GradeAddFragment : Fragment() {
    private lateinit var student: Student
    private lateinit var course: Course

    lateinit var gradeViewModel: GradeViewModel
    lateinit var studentCourseViewModel: StudentCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable(ARG_STUDENT)!!
            course = it.getParcelable(ARG_COURSE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gradeViewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        studentCourseViewModel = ViewModelProvider(requireActivity()).get(StudentCourseViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grade_add, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnAddGrade.setOnClickListener{
            var gradeInDouble = editTextGrade.text.toString().toDoubleOrNull()
            if (gradeInDouble != null)
                gradeViewModel.addGrade(student.id,course.id,  gradeInDouble.toDouble(), editTextGradeNote.text.toString())
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(student: Student, course: Course) =
            GradeAddFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_STUDENT, student)
                    putParcelable(ARG_COURSE, course)
                }
            }
    }
}