package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.StudentCourse
import com.example.teacherassistant.models.repositories.CourseRepository
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application): AndroidViewModel(application) {
    val courses:LiveData<List<Course>> =
        AppDatabase.getDatabase(application).courseDao().getAll()

    private val courseRepository:CourseRepository =
        CourseRepository(AppDatabase.getDatabase(application).courseDao())
//    private val studentCourseRepository:StudentCourseRepository =
//        StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    init {
//        viewModelScope.launch {
//            studentCourseRepository.add(StudentCourse(1, 1 ,1))
//        }
    }

    fun addCourse(name: String){
        viewModelScope.launch {
            courseRepository.add(Course(0,name))
        }
    }
}