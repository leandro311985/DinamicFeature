package com.example.dinamicfeature.presentation.profile.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemPhotoProfileBinding

class ProfileImageAdapter(private var context: Context, private val photos: List<Int>) : RecyclerView.Adapter<ProfileImageAdapter.MainViewPagerHolder>() {
  override fun onCreateViewHolder(container: ViewGroup, viewType: Int): MainViewPagerHolder {
    return MainViewPagerHolder(
      ItemPhotoProfileBinding.inflate(LayoutInflater.from(container.context), container, false)
    )
  }

  override fun onBindViewHolder(holder: MainViewPagerHolder, position: Int) {
    holder.bind(photos[position], context)
  }

  override fun getItemCount(): Int {
    return photos.size
  }

  class MainViewPagerHolder(private val bindingItem: ItemPhotoProfileBinding) : RecyclerView.ViewHolder(bindingItem.root) {
    fun bind(data: Int, context: Context) {
      bindingItem.imageItemProfile.setImageResource(data)

    }
  }
}
