package com.example.appsquola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsquola.model.Course
import com.example.appsquola.services.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesListActivity : AppCompatActivity() {
    lateinit var coursesList: RecyclerView
    lateinit var coursesNumberString: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_list)

        coursesList = findViewById(R.id.coursesList)
        coursesNumberString = findViewById(R.id.totalCoursesNumberText)

//        val memoryCourses = InMemoryCourseRepository.courses
//
        val layoutManager = LinearLayoutManager(this)
//
//        val adapter = CoursesListAdapter(memoryCourses)
        coursesList.layoutManager = layoutManager
//        coursesList.adapter = adapter
        loadCourses()
    }

    override fun onResume() {
        super.onResume()
        loadCourses()
    }

    private fun loadCourses() {
        val courseService = ServiceBuilder.buildService(CourseService::class.java)
        val requestCall = courseService.getCourses()

        requestCall.enqueue(object: Callback<List<Course>> { // Classe interna anonima
            // object: Callback<List<Course>> = il compilatore crea una classe, di cui non conosciamo
            // il nome, che estende l'interfaccia Callback<List<Course>>. Dopo, crea un oggetto di quella
            // classe.
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                if (response.isSuccessful) {
                    val courses = response.body()!!
                    coursesNumberString.text = String.format(resources.getString(R.string.course_count_string), courses.size)
                    coursesList.adapter = CoursesListAdapter(courses, this@CoursesListActivity)
                } else {
                    Toast.makeText(this@CoursesListActivity, "Failed to retrieve items", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Toast.makeText(this@CoursesListActivity,
                    "Error Occurred" + t.toString(), Toast.LENGTH_LONG).show()

            }
        })
    }
}