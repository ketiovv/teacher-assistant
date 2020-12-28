package com.example.teacherassistant.views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.adapters.ReportAdapter
import com.example.teacherassistant.viewmodels.CourseViewModel
import com.example.teacherassistant.viewmodels.GradeViewModel
import com.example.teacherassistant.viewmodels.StudentCourseViewModel
import com.example.teacherassistant.viewmodels.StudentViewModel
import kotlinx.android.synthetic.main.fragment_report.*
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentCourseViewModel: StudentCourseViewModel
    private lateinit var gradeViewModel: GradeViewModel
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var courseViewModel: CourseViewModel

    private lateinit var reportAdapter:ReportAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentCourseViewModel = ViewModelProvider(requireActivity()).get(StudentCourseViewModel::class.java)
        gradeViewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)

        viewManager = LinearLayoutManager(context)
        reportAdapter = ReportAdapter(
                gradeViewModel.currentDayGrades,
                { x -> studentViewModel.getStudentLastName(studentCourseViewModel.getStudentId(x))},
                { x -> courseViewModel.getCourseName(studentCourseViewModel.getCourseId(x)) }
        )

        gradeViewModel.currentDayGrades.observe(viewLifecycleOwner,{
            reportAdapter.notifyDataSetChanged()
        })

        studentViewModel.allStudents.observe(viewLifecycleOwner){}
        studentCourseViewModel.courseStudents.observe(viewLifecycleOwner){}
        courseViewModel.allCourses.observe(viewLifecycleOwner){}

        gradeViewModel.currentDate.observe(viewLifecycleOwner){ cal ->
            val format = SimpleDateFormat("dd/MM/yyyy")
            textViewDateValue.text = format.format(cal.time).toString()
            reportAdapter.notifyDataSetChanged()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBackDate.setOnClickListener {
            gradeViewModel.setYesterday()
        }
        buttonNextDate.setOnClickListener {
            gradeViewModel.setTomarrow()
        }

        recyclerViewReport.apply{
            layoutManager = viewManager
            adapter = reportAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReportFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}