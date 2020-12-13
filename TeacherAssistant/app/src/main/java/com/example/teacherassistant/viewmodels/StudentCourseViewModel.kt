package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.StudentCourse
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class StudentCourseViewModel(application: Application):AndroidViewModel(application) {

    val courseStudents = AppDatabase.getDatabase(application).studentCourseDao().getAll()
    val courseStudentsRepository =
        StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    fun addStudentToCourse(student_id:Int, course_id:Int){
        viewModelScope.launch {
            StudentCourse(0,student_id,course_id)
        }
    }
}