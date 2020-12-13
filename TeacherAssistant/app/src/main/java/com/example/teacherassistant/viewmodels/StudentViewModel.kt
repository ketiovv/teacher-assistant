package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import com.example.teacherassistant.models.repositories.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application):AndroidViewModel(application) {
    val courseId = MutableLiveData<Int>()
    val students:LiveData<List<Student>>

    private val studentRepository:StudentRepository =
        StudentRepository(AppDatabase.getDatabase(application).studentDao())
    private val studentCourseRepository:StudentCourseRepository =
        StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    init{
        students = Transformations.switchMap(courseId) { id ->
            if(id == 0) {
                return@switchMap studentRepository.getAll
            }
            else {
                return@switchMap studentRepository.getStudentsInCourse(id)
            }
        }
    }

    fun setCourseId(id: Int){
        courseId.value = id
    }

    fun addStudent(firstName:String, lastName:String){
        viewModelScope.launch {
            studentRepository.add(Student(0,firstName,lastName))
        }
    }

}