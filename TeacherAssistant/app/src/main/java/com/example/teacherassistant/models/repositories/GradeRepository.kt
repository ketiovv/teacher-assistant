package com.example.teacherassistant.models.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.models.dao.GradeDao
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Grade

class GradeRepository(private val gradeDao: GradeDao) {
    val readAll:LiveData<List<Grade>> = gradeDao.getAll()
    fun readAllGradesForStudentInCourse(studentId:Int, courseId:Int): LiveData<List<Grade>> = gradeDao.getAllByStudent(studentId,courseId)
    suspend fun add(grade: Grade) = gradeDao.insert(grade)
    suspend fun delete(grade:Grade) = gradeDao.delete(grade)

}