package com.example.teacherassistant.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM STUDENT_TABLE")
    fun getAll(): LiveData<List<Student>>

    @Query("SELECT * FROM STUDENT_TABLE WHERE ID=:id LIMIT 1")
    fun getById(id:Int): LiveData<Student>

    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    // TODO: @Update
}