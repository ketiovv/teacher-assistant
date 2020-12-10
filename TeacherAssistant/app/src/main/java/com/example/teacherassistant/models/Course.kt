package com.example.teacherassistant.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="course_table")
data class Course(@PrimaryKey(autoGenerate = true)
                  val id:Int,
                  val name:String)