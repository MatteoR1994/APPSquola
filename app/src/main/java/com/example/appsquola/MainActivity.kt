package com.example.appsquola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var readButton: Button

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