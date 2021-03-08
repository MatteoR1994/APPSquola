package com.example.appsquola

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsquola.model.Course

class CoursesListAdapter(private val coursesList: List<Course>, val context: CoursesListActivity) : RecyclerView.Adapter<CoursesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.courses_list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.course = coursesList[position]
//        holder.id.text = holder.course!!.id.toString()
//        holder.title.text = holder.course!!.title
//        holder.numHours.text = holder.course!!.numHours.toString()
//        holder.description.text = holder.course!!.description
//        holder.cost.text = holder.course!!.cost.toString()
        holder.id.text = String.format(context.resources.getString(R.string.course_id_string), holder.course!!.id)
        holder.title.text = String.format(context.resources.getString(R.string.course_title_string), holder.course!!.title)
        holder.numHours.text = String.format(context.resources.getString(R.string.course_hours_string), holder.course!!.numHours)
        holder.description.text = String.format(context.resources.getString(R.string.course_descr_string), holder.course!!.description)
        holder.cost.text = String.format(context.resources.getString(R.string.course_cost_string), holder.course!!.cost.toString())
        holder.editionsNumber.text = String.format(context.resources.getString(R.string.course_editions_string), holder.course!!.editions.size)

        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context,CourseDetailsActivity::class.java)
            intent.putExtra("courseId", holder.course!!.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.courseIdText)
        val title: TextView = itemView.findViewById(R.id.courseTitleText)
        val numHours: TextView = itemView.findViewById(R.id.courseNumHoursText)
        val description: TextView = itemView.findViewById(R.id.courseDescriptionText)
        val cost: TextView = itemView.findViewById(R.id.courseCostText)
        val editionsNumber: TextView = itemView.findViewById(R.id.courseEditionsNumberText)
        var course: Course? = null
    }
}