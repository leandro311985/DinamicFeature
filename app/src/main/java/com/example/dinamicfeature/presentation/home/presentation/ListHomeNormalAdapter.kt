package com.example.dinamicfeature.presentation.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemPeopleListHomeBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.extension.getDrawableByName
import com.example.extension.getStringByName
import com.squareup.picasso.Picasso


class ListHomeNormalAdapter(
  private val homeListener: HomeListener,
  private val context: Context,
  private val profiles: List<PersonsFake>
) : RecyclerView.Adapter<ListHomeNormalAdapter.MyViewHolder>() {
  private val data = profiles

  class MyViewHolder(private val view: ItemPeopleListHomeBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(data: PersonsFake, context: Context, homeListener: HomeListener) {
      val image = view.imageItem
      view.titleRc.text = context.getStringByName(data.name)
      val img = context.getDrawableByName(data.image[0])
      Picasso.get().load(img).into(image)
      view.cardViewPeople.setOnClickListener {
        homeListener.onItemClick(data, position)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder(
      ItemPeopleListHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(data[position], context, homeListener)
  }

  override fun getItemCount() = data.size
}