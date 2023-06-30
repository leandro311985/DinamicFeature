package com.example.dinamicfeature.presentation.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemEmptyListBinding
import com.example.dinamicfeature.databinding.ItemFavoritesMacthsBinding
import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.extension.getDrawableByName
import com.squareup.picasso.Picasso

class FavoritesMacthAdapter(
  private val context: Context,
  private val profiles: List<MyPersonsFake>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private val data = profiles
  private val VIEW_TYPE_ITEM = 0
  private val VIEW_TYPE_EMPTY = 1

  class EmptyViewHolder(itemView: ItemEmptyListBinding) : RecyclerView.ViewHolder(itemView.root)

  class MyViewHolder(private val view: ItemFavoritesMacthsBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(data: MyPersonsFake, context: Context) {
      val image = view.cardViewMatchs
      val img = context.getDrawableByName(data.personsFake.image)
      Picasso.get().load(img).into(image)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)

    return if (viewType == VIEW_TYPE_ITEM) {
      val itemView = ItemFavoritesMacthsBinding.inflate(inflater)
      MyViewHolder(itemView)
    } else {
      val emptyView = ItemEmptyListBinding.inflate(inflater)
      EmptyViewHolder(emptyView)
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is MyViewHolder) {
      val item = data[position]
      holder.bind(item, context)
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (data.isEmpty()) {
      VIEW_TYPE_EMPTY
    } else {
      VIEW_TYPE_ITEM
    }
  }

  override fun getItemCount(): Int {
    return if (data.isEmpty()) {
      1
    } else {
      data.size
    }
  }
}