package com.example.teacherassistant.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(@PrimaryKey(autoGenerate = true)
                   val id: Int,
                   var firstName:String,
                   var lastName:String)