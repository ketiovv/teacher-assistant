package com.example.teacherassistant.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.models.entities.Grade

@Dao
interface GradeDao {
    @Query("SELECT * FROM grade_table")
    fun getAll(): LiveData<List<Grade>>

    @Query("SELECT * FROM grade_table INNER JOIN student_course_table ON grade_table.studentCourseId = student_course_table.id WHERE student_course_table.student_id = :studentId AND student_course_table.course_id = :courseId")
    fun getAllByStudent(studentId:Int, courseId:Int): LiveData<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)
}