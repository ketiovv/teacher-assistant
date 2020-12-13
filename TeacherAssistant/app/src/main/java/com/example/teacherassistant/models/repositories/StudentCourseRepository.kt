package com.example.teacherassistant.models.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.models.dao.StudentCourseDao
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.StudentCourse

class StudentCourseRepository(val studentCourseDao: StudentCourseDao) {
    val readAll: LiveData<List<StudentCourse>> = studentCourseDao.getAll()

    suspend fun add(studentCourse:StudentCourse) = studentCourseDao.addStudentToCourse(studentCourse)
}