package com.example.teacherassistant.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teacherassistant.models.enums.GradeValue
import java.util.*

@Entity(tableName = "grade_table")
data class Grade(@PrimaryKey(autoGenerate = true)
                 val id:Int,
                 val studentCourseId:Int,
                 val grade: GradeValue,
                 val note: String,
                 val date: String)