package com.example.teacherassistant.models.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.dao.CourseDao

class CourseRepository(private val courseDao: CourseDao) {
    val readAll:LiveData<List<Course>> = courseDao.getAll()
    fun readAllCoursesForStudent(studentId:Int):LiveData<List<Course>> = courseDao.getAllCoursesForStudent(studentId)
    suspend fun add(course: Course) = courseDao.insert(course)
    suspend fun delete(course: Course) = courseDao.delete(course)
    suspend fun update(course: Course) = courseDao.update(course)
}