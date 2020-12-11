package com.example.teacherassistant.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.adapters.CourseListAdapter
import com.example.teacherassistant.viewmodels.CourseListViewModel
import kotlinx.android.synthetic.main.fragment_course_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
// --- mine code ---
private lateinit var courseListViewModel: CourseListViewModel
// --- --- --- --- ---


/**
 * A simple [Fragment] subclass.
 * Use the [CourseListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // --- mine code ---
    private lateinit var courseListAdapter: CourseListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    // --- --- --- --- ---

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
        // --- mine code ---

        courseListViewModel = ViewModelProvider(requireActivity())
            .get(CourseListViewModel::class.java)
        viewManager = LinearLayoutManager(context)
        courseListAdapter = CourseListAdapter(courseListViewModel.courses)

        courseListViewModel.courses.observe(viewLifecycleOwner,{
            courseListAdapter.notifyDataSetChanged()
        })

        // --- --- --- --- ---


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- mine code ---
        recyclerViewCourses.apply{
            adapter = courseListAdapter
            layoutManager = viewManager
        }

        buttonNewCourse.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_courseListFragment_to_courseAddFragment)
        }
        // --- --- --- --- ---
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}