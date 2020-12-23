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
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class GradeViewModel(application: Application): AndroidViewModel(application) {
    private val gradeRepository = GradeRepository(AppDatabase.getDatabase(application).gradeDao())
    private val studentCourseRepository = StudentCourseRepository(AppDatabase.getDatabase(application).studentCourseDao())

    val studentId = MutableLiveData<Int>()
    val courseId = MutableLiveData<Int>()

    val grades:LiveData<List<Grade>>
    val allGrades:LiveData<List<Grade>>
    var todaysGrades : LiveData<List<Grade>>

    var reportDate: Calendar

    val pairValues = MediatorLiveData<Pair<Int, Int>>().apply {
        addSource(studentId) {
            value = Pair(it, courseId.value!!)
        }
        addSource(courseId) {
            value = Pair(studentId.value!!, it)
        }
    }

    init {
        reportDate = Calendar.getInstance().also { c ->
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0)
        }
        allGrades = gradeRepository.readAll
        grades = Transformations.switchMap(pairValues) { pair ->
            val studentId = pair.first
            val courseId = pair.second

            return@switchMap gradeRepository.readAllGradesForStudentInCourse(studentId, courseId)
        }


        // za kazdym razem jak zmienia sie allGrades - todaysGrades tez sie zupdateuja
        todaysGrades = Transformations.map(allGrades) { grade ->
            grade.filterNot { x -> x.date.before(reportDate.time )}
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addGrade(studentId:Int, courseId:Int, grade: Double, note: String){
        viewModelScope.launch {
            gradeRepository.add(Grade(0,studentCourseRepository.getId(studentId,courseId), grade, note, Calendar.getInstance().time))
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