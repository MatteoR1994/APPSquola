package com.example.appsquola.services

import com.example.appsquola.model.Course
import com.example.appsquola.model.CourseEdition
import retrofit2.Call
import retrofit2.http.*

interface CourseService {
    @GET("course/")
    fun getCourses(): Call<List<Course>>

    @GET("course/{id}/")
    fun getCourseById(@Path("id") id: Long): Call<Course>

    @DELETE("edition/{id}/")
    fun deleteEditionById(@Path("id") id: Long): Call<Unit>

    @POST("course/{id}/editions")
    fun addEdition(@Body newEdition: CourseEdition, @Path("id") id: Long): Call<CourseEdition>

    @PUT("course/{id}/")
    fun updateCourse(@Body course: Course, @Path("id") id: Long): Call<Course>
}