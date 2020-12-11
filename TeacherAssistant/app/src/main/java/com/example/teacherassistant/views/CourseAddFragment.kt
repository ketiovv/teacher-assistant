package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.viewmodels.CourseListViewModel
import kotlinx.android.synthetic.main.fragment_course_add.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CourseAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var courseListViewModel:CourseListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseListViewModel = ViewModelProvider(requireActivity())
            .get(CourseListViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddCourse.setOnClickListener{
            if (!editTextNewCourseName.text.toString().isNullOrBlank()){
                courseListViewModel.addCourse(editTextNewCourseName.text.toString())
            }
            navigateToCourseList(view)
        }
        buttonCancelCourse.setOnClickListener{
            editTextNewCourseName.text.clear()
            navigateToCourseList(view)
        }

    }
    private fun navigateToCourseList(view : View){
        view.findNavController().navigate(R.id.action_courseAddFragment_to_courseListFragment)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}