package com.example.teacherassistant.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.models.dao.CourseDao
import com.example.teacherassistant.models.dao.GradeDao
import com.example.teacherassistant.models.dao.StudentCourseDao
import com.example.teacherassistant.models.dao.StudentDao
import com.example.teacherassistant.models.entities.Course
import com.example.teacherassistant.models.entities.Grade
import com.example.teacherassistant.models.entities.Student
import com.example.teacherassistant.models.entities.StudentCourse

@Database(
    entities = [Student::class,
                Course::class,
                StudentCourse::class,
                Grade::class],
    version = 3,
    exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun studentDao(): StudentDao
    abstract fun studentCourseDao(): StudentCourseDao
    abstract fun gradeDao():GradeDao

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase?=null

        fun getDatabase(context:Context):AppDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            else{
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }
            }

        }

    }
}