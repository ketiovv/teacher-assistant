package com.example.teacherassistant.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.models.AppDatabase
import com.example.teacherassistant.models.Student
import com.example.teacherassistant.models.repositories.StudentRepository
import kotlinx.coroutines.launch

class StudentListViewModel(application: Application):AndroidViewModel(application) {
    val students:LiveData<List<Student>> =
        AppDatabase.getDatabase(application).studentDao().getAll()
    private val studentRepository:StudentRepository =
        StudentRepository(AppDatabase.getDatabase(application).studentDao())

    fun addStudent(firstName:String, lastName:String){
        viewModelScope.launch {
            studentRepository.add(Student(0,firstName,lastName))
        }
    }
}