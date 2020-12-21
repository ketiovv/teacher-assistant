package com.example.teacherassistant.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.entities.StudentCourse

@Dao
interface StudentCourseDao {
    @Query("SELECT * FROM student_course_table")
    fun getAll():LiveData<List<StudentCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudentToCourse(studentCourse:StudentCourse)

    @Query("DELETE FROM student_course_table WHERE student_id = :student_id AND course_id = :course_id")
    suspend fun deleteStudentFromCourse(course_id: Int, student_id: Int)

    @Query("SELECT * FROM student_course_table WHERE course_id ==:courseId AND student_id == :studentId LIMIT 1")
    suspend fun getIdByStudentAndCourse(courseId:Int,studentId:Int):StudentCourse

    @Query("SELECT student_id FROM student_course_table WHERE id == :studentCourseId")
    suspend fun getStudentId(studentCourseId:Int): Int
}