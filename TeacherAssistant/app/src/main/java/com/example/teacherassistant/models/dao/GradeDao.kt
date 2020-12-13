package com.example.teacherassistant.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.teacherassistant.models.entities.Grade

@Dao
interface GradeDao {
    @Query("SELECT * FROM COURSE_TABLE")
    fun getAll(): LiveData<List<Grade>>

    @Query("SELECT * FROM COURSE_TABLE WHERE ID=:studentId")
    fun getAllByStudent(studentId:Int): LiveData<List<Grade>>

    @Insert
    suspend fun insert(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)

    // TODO: @Update
}