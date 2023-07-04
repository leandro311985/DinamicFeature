package com.example.dinamicfeature.presentation.searchpeople.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.navigation.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.databinding.ItemPeopleListBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.extension.getDrawableByName
import com.example.extension.getStringByName
import com.squareup.picasso.Picasso

class PeopleCardAdapter(
  private val cardListener: CardListener,
  private val context: Context, private val cards: List<PersonsFake>
) : BaseAdapter() {

  @SuppressLint("ViewHolder")
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val card = cards[position]
    val binding = ItemPeopleListBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    val img = context.getDrawableByName(card.image[0])
    val title = context.getStringByName(card.name)
    val subTitle = context.getStringByName(card.end)
    Picasso.get().load(img).into(binding.imageItem)
    binding.titleRc.text = title
    binding.subTitleRc.text = subTitle

    binding.cardViewPeople.setOnClickListener {
      cardListener.onItemClick(card,position)
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
