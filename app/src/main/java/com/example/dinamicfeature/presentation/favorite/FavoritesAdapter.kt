package com.example.dinamicfeature.presentation.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemFavoritesMacthsBinding
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.extension.getDrawableByName
import com.squareup.picasso.Picasso

class FavoritesAdapter(
  private val context: Context,
  private val profiles: List<PersonsFakeHome>
) : RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>() {
  private val data = profiles

  class MyViewHolder(private val view: ItemFavoritesMacthsBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(data: PersonsFakeHome, context: Context) {
      val image = view.cardViewMatchs
      val img = context.getDrawableByName(data.image)
      Picasso.get().load(img).into(image)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder(
      ItemFavoritesMacthsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(data[position], context)
  }

  override fun getItemCount() = data.size
}