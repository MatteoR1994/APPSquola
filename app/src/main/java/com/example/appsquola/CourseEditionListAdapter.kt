package com.example.appsquola


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsquola.model.Course
import com.example.appsquola.model.CourseEdition
import com.example.appsquola.services.CourseService
import kotlinx.android.synthetic.main.activity_course_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseEditionListAdapter(private val coursesEditionList: List<CourseEdition>,private val context:CourseDetailsActivity) : RecyclerView.Adapter< CourseEditionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_edition_list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.courseEdition = coursesEditionList[position]
        holder.id.text = holder.courseEdition!!.id.toString()
        holder.code.text = holder.courseEdition!!.code
        holder.start.text = holder.courseEdition!!.start.toString()
        holder.end.text = holder.courseEdition!!.end
        holder.courseId.text = holder.courseEdition!!.courseId.toString()
        holder.courseTitle.text = holder.courseEdition!!.courseTitle.toString()


       holder.deleteButton.setOnClickListener { v ->
           val courseService = ServiceBuilder.buildService(CourseService::class.java)
           val requestCall = courseService.deleteEditionById(holder.courseEdition!!.id)

           requestCall.enqueue(object: Callback<Unit> { // Classe interna anonima
               // object: Callback<List<Course>> = il compilatore crea una classe, di cui non conosciamo
               // il nome, che estende l'interfaccia Callback<List<Course>>. Dopo, crea un oggetto di quella
               // classe.
               override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                   if (response.isSuccessful) {
                       val courses = response.body()!!
                       context.loadDetails(holder.courseEdition!!.courseId)

                   } else {
                       Toast.makeText(this@CourseEditionListAdapter.context, "Failed to delete edition ${response.code()}", Toast.LENGTH_LONG).show()
                   }
               }

               override fun onFailure(call: Call<Unit>, t: Throwable) {
                   Toast.makeText(this@CourseEditionListAdapter.context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG).show()
               }
           })



        }
    }

    override fun getItemCount(): Int {
        return coursesEditionList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.ceIDText)
        val code: TextView = itemView.findViewById(R.id.ceCodeText)
        val start: TextView = itemView.findViewById(R.id.ceStartText)
        val end: TextView = itemView.findViewById(R.id.ceEndText)
        val courseId: TextView = itemView.findViewById(R.id.ceCourseIdText)
        val courseTitle: TextView = itemView.findViewById(R.id.ceCourseTitleText)
        var courseEdition : CourseEdition? = null
        val deleteButton: ImageButton =itemView.findViewById(R.id.deleteBtn)
    }
}