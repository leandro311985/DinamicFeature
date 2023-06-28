package com.example.dinamicfeature.presentation.explore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemPeopleListBinding
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.extension.getDrawableByName
import com.squareup.picasso.Picasso

class ListExploreAdapter(
  private val context: Context, private val cards: List<PersonsFakeHome>
) : RecyclerView.Adapter<ListExploreAdapter.ViewHolder>() {

  // create new views
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ItemPeopleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    return ViewHolder(binding)
  }

  // binds the list items to a view
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val itemsViewModel = cards[position]
    Picasso.get().load(context.getDrawableByName(itemsViewModel.image)).into( holder.photo)

  }

  // return the number of the items in the list
  override fun getItemCount(): Int {
    return cards.size
  }

  // Holds the views for adding it to image and text
  class ViewHolder(binding: ItemPeopleListBinding) : RecyclerView.ViewHolder(binding.root) {
    val photo = binding.imageItem

  }
}
