package com.example.teacherassistant.models.entities

import androidx.room.*
import com.example.teacherassistant.models.converters.DateConverter
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
@TypeConverters(DateConverter::class)
data class Grade(@PrimaryKey(autoGenerate = true)
                 val id:Int,
                 val studentCourseId:Int,
                 val grade: Double,
                 val note: String,
                 val date: Date)