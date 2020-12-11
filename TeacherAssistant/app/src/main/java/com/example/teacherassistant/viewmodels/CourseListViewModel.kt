package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.Course
import com.example.teacherassistant.models.repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseListViewModel(application: Application): AndroidViewModel(application) {
    val courses:LiveData<List<Course>> =
        AppDatabase.getDatabase(application).courseDao().getAll()
    private val courseRepository:CourseRepository =
        CourseRepository(AppDatabase.getDatabase(application).courseDao())

    init {
        viewModelScope.launch {
            courseRepository.add(Course(0,"test"))
        }
    }
    // TODO: Add some test courses
}