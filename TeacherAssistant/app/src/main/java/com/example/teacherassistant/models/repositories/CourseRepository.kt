package com.example.teacherassistant.models.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.models.Course
import com.example.teacherassistant.models.CourseDao

class CourseRepository(private val courseDao: CourseDao) {
    val readAll:LiveData<List<Course>> = courseDao.getAll()

    suspend fun add(course: Course) = courseDao.insert(course)
    suspend fun delete(course: Course) = courseDao.delete(course)
}