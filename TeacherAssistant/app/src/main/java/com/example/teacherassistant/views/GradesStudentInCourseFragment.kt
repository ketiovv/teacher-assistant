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
import com.example.teacherassistant.adapters.GradeListAdapter
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.repositories.GradeRepository
import com.example.teacherassistant.viewmodels.GradeViewModel
import kotlinx.android.synthetic.main.fragment_grades_student_in_course.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_STUDENT = "student"
private const val ARG_COURSE = "course"

class GradesStudentInCourseFragment : Fragment() {
private lateinit var student:Student
private lateinit var course:Course

private lateinit var viewManager: LinearLayoutManager
private lateinit var gradesAdapter:GradeListAdapter

private lateinit var gradeViewModel:GradeViewModel

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
        gradeViewModel = ViewModelProvider(requireActivity())
            .get(GradeViewModel::class.java)
        gradeViewModel.setStudentAndCourseId(student.id,course.id)
        viewManager = LinearLayoutManager(context)
        gradesAdapter = GradeListAdapter(gradeViewModel.grades) { x ->
            gradeViewModel.deleteGrade(x)
        }

        gradeViewModel.grades.observe(viewLifecycleOwner,{
            gradesAdapter.notifyDataSetChanged()
        })


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades_student_in_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewGradesCourseName.text = course.name
        textViewGradesStudentLastName.text = student.lastName
        buttonAddGrade.setOnClickListener{ view ->
            view.findNavController().navigate(
                R.id.action_gradesStudentInCourseFragment_to_gradeAddFragment,
                bundleOf("student" to student, "course" to course))
        }

        recyclerViewGrades.apply {
            layoutManager = viewManager
            adapter = gradesAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(course:Course,student:Student) =
            GradesStudentInCourseFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COURSE, course)
                    putParcelable(ARG_STUDENT, student)
                }
            }
    }
}