package com.example.appsquola.services

import com.example.appsquola.model.Course
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseService {
    @GET("course/")
    fun getCourses(): Call<List<Course>>

    @GET("course/{id}/")
    fun getCourseById(@Path("id") id: Long): Call<Course>
}