package com.example.teacherassistant.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.models.entities.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM COURSE_TABLE")
    fun getAll():LiveData<List<Course>>

    @Query("SELECT * FROM COURSE_TABLE WHERE ID=:id LIMIT 1")
    fun getById(id:Int):LiveData<Course>

    @Insert
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(course: Course)

    // TODO: @Update
}