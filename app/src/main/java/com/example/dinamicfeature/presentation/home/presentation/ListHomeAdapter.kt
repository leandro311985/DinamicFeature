package com.example.dinamicfeature.presentation.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.R
import com.example.dinamicfeature.databinding.ItemPeopleListBinding
import com.example.dinamicfeature.databinding.ItemPeopleListHomeBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.presentation.searchpeople.adapter.CardListener
import com.example.extension.getDrawableByName
import com.example.extension.getStringByName
import com.squareup.picasso.Picasso

class ListHomeAdapter(
  private val homeListener: HomeListener,
  private val context: Context, private val cards: List<PersonsFake>
) : BaseAdapter() {

  @SuppressLint("ViewHolder")
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val card = cards[position]

    val binding = ItemPeopleListHomeBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    val img = context.getDrawableByName(card.image)
    val title = context.getStringByName(card.name)
    Picasso.get().load(img).into(binding.imageItem)
    binding.titleRc.text = title

    binding.cardViewPeople.setOnClickListener {
      homeListener.onItemClick(card, position)
    }

    return binding.root
  }

  override fun getItem(position: Int): Any {
    return cards[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getCount(): Int {
    return cards.size
  }


}
