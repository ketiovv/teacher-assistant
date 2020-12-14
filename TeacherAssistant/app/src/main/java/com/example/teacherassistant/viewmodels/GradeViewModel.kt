package com.example.teacherassistant.viewmodels

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.entities.Grade
import com.example.teacherassistant.models.repositories.GradeRepository
import com.example.teacherassistant.models.repositories.StudentCourseRepository
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class GradeViewModel(application: Application): AndroidViewModel(application) {
    private val gradeRepository = GradeRepository(AppDatabase.getDatabase(application).gradeDao())
    private val studentCourseRepository = StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    val studentId = MutableLiveData<Int>()
    val courseId = MutableLiveData<Int>()

    val grades:LiveData<List<Grade>>
    val allGrades:LiveData<List<Grade>>
    var todaysGrades : LiveData<List<Grade>>

    val combinedValues = MediatorLiveData<Pair<Int, Int>>().apply {
        addSource(studentId) {
            value = Pair(it, courseId.value!!)
        }
        addSource(courseId) {
            value = Pair(studentId.value!!, it)
        }
    }

    init {
        allGrades = gradeRepository.readAll
        grades = Transformations.switchMap(combinedValues) {
            pair -> val studentId = pair.first
            val courseId = pair.second
            return@switchMap gradeRepository.readAllGradesForStudentInCourse(studentId, courseId)
        }



        todaysGrades = Transformations.map(allGrades) {
                grade -> grade.filter { x ->
            x.date.substring(0, 10) == LocalDateTime.now().toString().substring(0, 10)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addGrade(studentId:Int, courseId:Int, grade: String, note: String){
        viewModelScope.launch {
            var dateNow = LocalDateTime.now();
            gradeRepository.add(Grade(0,studentCourseRepository.getId(studentId,courseId), grade, note, dateNow.toString()))
        }
    }

    fun deleteGrade(grade: Grade) {
        viewModelScope.launch {
            gradeRepository.delete(grade)
        }
    }

    fun setStudentAndCourseId(student_id: Int, course_id: Int){
        this.studentId.value = student_id
        this.courseId.value = course_id
    }

}