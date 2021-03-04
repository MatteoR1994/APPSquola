package com.example.appsquola.model

import java.time.LocalDate

class CourseEdition(var id: Long, var code: String, var start: LocalDate, var end: LocalDate,
                    var courseId: Long, var courseTitle: String) {
}