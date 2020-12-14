package com.example.teacherassistant.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.teacherassistant.models.entities.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM STUDENT_TABLE")
    fun getAll(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table INNER JOIN student_course_table ON student_table.id = student_course_table.student_id WHERE student_course_table.course_id = :course_id")
    fun getByCourseId(course_id:Int): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id NOT IN (SELECT student_id FROM student_course_table WHERE course_id = :courseId)")
    fun getNotInCourseById(courseId: Int): LiveData<List<Student>>

    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

}