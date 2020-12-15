package com.example.teacherassistant.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "grade_table",
        foreignKeys = [
            androidx.room.ForeignKey(
                entity = StudentCourse::class,
                parentColumns = ["id"],
                childColumns = ["studentCourseId"],
                onDelete = ForeignKey.CASCADE
    )]
)
data class Grade(@PrimaryKey(autoGenerate = true)
                 val id:Int,
                 val studentCourseId:Int,
                 val grade: String,
                 val note: String,
                 val date: String)