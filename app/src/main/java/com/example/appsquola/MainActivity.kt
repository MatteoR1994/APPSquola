package com.example.appsquola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var readButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readButton = findViewById(R.id.readCoursesButton)

        readButton.setOnClickListener {
            val intent = Intent(this, CoursesListActivity::class.java)
            startActivity(intent)
        }
    }
}