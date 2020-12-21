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

    val studentsNotInCourse:LiveData<List<Student>>


    init{
        // przy zmianie parametru - w tym przypadku courseId funkcja zostanie znowu wywołana
        students = Transformations.switchMap(courseId) { id ->
            if(id == 0) {
                return@switchMap studentRepository.getAll
            }
            else {
                return@switchMap studentRepository.getStudentsInCourse(id)
            }
        }
        studentsNotInCourse = Transformations.switchMap(courseId){
                id ->
            if(id == 0) {
                return@switchMap studentRepository.getAll
            }
            else {
                return@switchMap studentRepository.getStudentsNotInCourse(id)
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

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.delete(student)
        }
    }

    fun updateStudent(student: Student, newName: String, newSurname: String){
        viewModelScope.launch {
            studentRepository.update(Student(student.id, newName, newSurname))
        }
    }
}