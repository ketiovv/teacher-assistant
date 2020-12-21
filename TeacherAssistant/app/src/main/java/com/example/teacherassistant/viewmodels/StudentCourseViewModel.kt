package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.entities.StudentCourse
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StudentCourseViewModel(application: Application):AndroidViewModel(application) {

    private val courseStudentsRepository =
        StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    val courseStudents = courseStudentsRepository.readAll

    var studentId: Int = 0


    fun addStudentToCourse(student_id:Int, course_id:Int){
        viewModelScope.launch {
            courseStudentsRepository.add(StudentCourse(0,student_id,course_id))
        }
    }

    fun deleteStudentFromCourse(student: Student, course: Course) {
        viewModelScope.launch {
            courseStudentsRepository.deleteStudentFromCourse(course.id,student.id)
        }
    }

    fun getStudentId(studentCourseId:Int): Int {
        viewModelScope.launch {
            studentId = courseStudentsRepository.getStudentId(studentCourseId)
        }
        return studentId
    }
}