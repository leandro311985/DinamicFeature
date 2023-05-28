package com.example.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.domain.models.Profile
import com.example.profile.R

class ProfileAdapter(private val profiles: List<Profile>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.line_start, parent, false)
    return ProfileViewHolder(view)
  }

  override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
    val profile = profiles[position]
    holder.profileNameTextView.text = profile.name
    holder.startDescriptionTitle.text = profile.type
    holder.startTitleData.text = profile.data
  }

  override fun getItemCount(): Int {
    return profiles.size
  }

  inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val profileIcon: AppCompatImageView = itemView.findViewById(R.id.profileIcon)
    val profileNameTextView: TextView = itemView.findViewById(R.id.startName)
    val startDescriptionTitle: TextView = itemView.findViewById(R.id.startDescriptionTitle)
    val startTitleData: TextView = itemView.findViewById(R.id.startTitleData)
  }
}
