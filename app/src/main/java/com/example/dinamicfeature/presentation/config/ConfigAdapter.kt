package com.example.dinamicfeature.presentation.config


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemConfigBinding


class ConfigAdapter(private val mList: List<ConfigData>) : RecyclerView.Adapter<ConfigAdapter.ViewHolder>() {

  // create new views
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ItemConfigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  // binds the list items to a view
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val itemsViewModel = mList[position]
    // sets the text to the textview from our itemHolder class
    holder.textView.text = itemsViewModel.item

  }

  // return the number of the items in the list
  override fun getItemCount(): Int {
    return mList.size
  }

  // Holds the views for adding it to image and text
  class ViewHolder(binding: ItemConfigBinding) : RecyclerView.ViewHolder(binding.root) {
    val textView = binding.textTitle
  }
}
