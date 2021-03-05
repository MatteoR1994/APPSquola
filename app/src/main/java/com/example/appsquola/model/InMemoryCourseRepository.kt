package com.example.appsquola.model

object InMemoryCourseRepository {
    val courses: MutableList<Course> = mutableListOf(
        Course(4,"Corso 1", 10, "ciao", 150.0, mutableSetOf()),
        Course(5,"Corso 2", 20, "prova", 250.0, mutableSetOf())
    )
//    val courses: MutableList<Course> = mutableListOf(
//            Course(4,"Corso 1", 10, "ciao", 150.0),
//            Course(5,"Corso 2", 20, "prova", 250.0)
//    )
}