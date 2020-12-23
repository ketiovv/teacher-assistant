package com.example.teacherassistant.models.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.models.dao.StudentCourseDao
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.StudentCourse

class StudentCourseRepository(private val studentCourseDao: StudentCourseDao) {
    val readAll: LiveData<List<StudentCourse>> = studentCourseDao.getAll()
    suspend fun add(studentCourse:StudentCourse) = studentCourseDao.addStudentToCourse(studentCourse)
    suspend fun deleteStudentFromCourse(course_id: Int, student_id: Int) = studentCourseDao.deleteStudentFromCourse(course_id, student_id)
    suspend fun getId(courseId:Int, studentId:Int): Int = studentCourseDao.getIdByStudentAndCourse(studentId,courseId).id
    suspend fun getStudentId(studentCourseId: Int): Int = studentCourseDao.getStudentId(studentCourseId)
    suspend fun getCourseId(studentCourseId: Int): Int = studentCourseDao.getCourseId(studentCourseId)

}