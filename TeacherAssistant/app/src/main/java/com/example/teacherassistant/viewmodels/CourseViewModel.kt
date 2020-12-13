package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.StudentCourse
import com.example.teacherassistant.models.repositories.CourseRepository
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application): AndroidViewModel(application) {
    val studentId = MutableLiveData<Int>()
    val courses:LiveData<List<Course>>

    private val courseRepository:CourseRepository =
        CourseRepository(AppDatabase.getDatabase(application).courseDao())


    init {
        courses = Transformations.switchMap(studentId) { id ->
            if(id == 0) {
                return@switchMap courseRepository.readAll
            }
            else {
                return@switchMap courseRepository.readAllCoursesForStudent(id)
            }
        }

    }

    fun setStudentId(id:Int){
        studentId.value = id
    }

    fun addCourse(name: String){
        viewModelScope.launch {
            courseRepository.add(Course(0,name))
        }
    }
}