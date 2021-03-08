package com.example.appsquola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsquola.model.Course
import com.example.appsquola.services.CourseService
import kotlinx.android.synthetic.main.activity_course_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseDetailsActivity : AppCompatActivity() {
    lateinit var editionsNumber: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        editionsNumber = findViewById(R.id.courseDetailsEditionsNumberText)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey("courseId")!!) {
            val id = intent.getLongExtra("courseId", 0)
            loadDetails(id)
        }
    }
    fun loadDetails(id: Long) {
        val courseService = ServiceBuilder.buildService(CourseService::class.java)
        val requestCall = courseService.getCourseById(id)

        requestCall.enqueue(object: Callback<Course> { // Classe interna anonima
            // object: Callback<List<Course>> = il compilatore crea una classe, di cui non conosciamo
            // il nome, che estende l'interfaccia Callback<List<Course>>. Dopo, crea un oggetto di quella
            // classe.
            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                if (response.isSuccessful) {
                    val courses = response.body()!!
                    courseIdField.setText(courses.id.toString())
                    courseTitleField.setText(courses.title)
                    courseHoursField.setText(courses.numHours.toString())
                    courseDescriptionField.setText(courses.description)
                    courseCostField.setText(courses.cost.toString())
                    val linearLayoutEdition = LinearLayoutManager(this@CourseDetailsActivity)
                    courseEditionList.layoutManager = linearLayoutEdition
                    //val adapterEdition = CourseEditionListAdapter(courses.editions.toList(),this@CourseDetailsActivity )
                    val adapterEdition = CourseEditionListAdapter(courses.editions.toMutableList(),this@CourseDetailsActivity )
                    courseEditionList.adapter = adapterEdition
                } else {
                    Toast.makeText(this@CourseDetailsActivity, "Failed to load course", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Course>, t: Throwable) {
                Toast.makeText(this@CourseDetailsActivity, "Error Occurred" + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}