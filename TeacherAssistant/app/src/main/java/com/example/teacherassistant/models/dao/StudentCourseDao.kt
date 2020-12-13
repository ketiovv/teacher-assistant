package com.example.teacherassistant.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.models.entities.StudentCourse

@Dao
interface StudentCourseDao {
    @Query("SELECT * FROM student_course_table")
    fun getAll():LiveData<List<StudentCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudentToCourse(studentCourse:StudentCourse)
}