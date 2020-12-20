package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.viewmodels.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_edit.*

private const val ARG_STUDENT = "student"

class StudentEditFragment : Fragment() {
    private lateinit var student: Student

    private lateinit var studentViewModel: StudentViewModel

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

        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textViewStudentInEditActualName.text = student.firstName
        textViewStudentInEditActualSurname.text = student.lastName

        editTextCourseNameAfterEditName.setText(student.firstName)
        editTextCourseNameAfterEditSurname.setText(student.lastName)

        buttonSaveStudentEdit.setOnClickListener {
            studentViewModel.updateStudent(
                student,
                editTextCourseNameAfterEditName.text.toString(),
                editTextCourseNameAfterEditSurname.text.toString())
        }

    }
    companion object {
        @JvmStatic
        fun newInstance(student: Student) =
            StudentEditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_STUDENT, student)
                }
            }
    }
}