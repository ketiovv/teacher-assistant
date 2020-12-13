package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Student
import kotlinx.android.synthetic.main.fragment_student_details.*

private const val ARG_STUDENT = "student"


class StudentDetailsFragment : Fragment() {
private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable(ARG_STUDENT)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewStudentFirstNameInDetails.text = student.firstName
        textViewStudentLastNameInDetails.text = student.lastName
    }

    companion object {
        @JvmStatic
        fun newInstance(student: Student) =
            StudentDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_STUDENT, student)
                }
            }
    }
}