package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.entities.StudentCourse
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class StudentCourseViewModel(application: Application):AndroidViewModel(application) {

    private val courseStudentsRepository =
        StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    val courseStudents = courseStudentsRepository.readAll


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

    fun getStudentId(studentCourseId:Int) : Int?{
        var test =courseStudentsRepository.readAll.value?.find { x -> x.id == studentCourseId }
        var test2 = test?.student_id
        return test2
    }

}