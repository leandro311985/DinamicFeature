package com.example.dinamicfeature.presentation.searchpeople.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.R
import com.example.dinamicfeature.presentation.searchpeople.data.Region

class SearchAdapter(
  // on below line we are passing variables as course list and context
  private var courseList: ArrayList<Region>,
) : RecyclerView.Adapter<SearchAdapter.CourseViewHolder>() {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CourseViewHolder {

    val itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.item_region,
      parent, false
    )

    return CourseViewHolder(itemView)
  }

  fun filterList(filterlist: ArrayList<Region>) {
    courseList = filterlist
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
    holder.courseNameTV.text = courseList[position].regionName
  }

  override fun getItemCount(): Int {
    return courseList.size
  }

  class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // on below line we are initializing our course name text view and our image view.
    val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
  }
}