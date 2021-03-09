package com.example.appsquola.model

class Course(var id: Long, var title: String, var numHours: Int, var description: String,
             var cost: Double, var editions: MutableSet<CourseEdition>) {
}
//class Course(var id: Long, var title: String, var numHours: Int, var description: String,
  //           var cost: Double) {
//}