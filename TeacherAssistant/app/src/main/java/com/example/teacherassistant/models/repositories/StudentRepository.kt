package com.example.teacherassistant.models.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.models.Student
import com.example.teacherassistant.models.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    val getAll:LiveData<List<Student>> = studentDao.getAll()
    suspend fun add(student: Student) = studentDao.insert(student)
    suspend fun delete(student: Student)= studentDao.delete(student)
}