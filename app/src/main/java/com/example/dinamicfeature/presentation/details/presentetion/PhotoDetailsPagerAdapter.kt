package com.example.dinamicfeature.presentation.details.presentetion

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.databinding.ItemHomeViewPageBinding
import com.example.dinamicfeature.databinding.ItemHomeViewPageInternoBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.extension.getDrawableByName
import com.example.extension.getStringByName
import com.squareup.picasso.Picasso


class PhotoDetailsPagerAdapter(private var listener: DetailsViewPagerListener, private var context: Context, private val photos: List<PersonsFake>) :
  RecyclerView.Adapter<PhotoDetailsPagerAdapter.MainViewHolder>() {
  override fun onCreateViewHolder(container: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(
      ItemHomeViewPageBinding.inflate(LayoutInflater.from(container.context), container, false)
    )
  }

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    holder.bind(photos[position], context, listener)
  }

  override fun getItemCount(): Int {
    return photos.size
  }

  class MainViewHolder(private val bindingItem: ItemHomeViewPageBinding) : RecyclerView.ViewHolder(bindingItem.root) {

    fun bind(data: PersonsFake, context: Context, listener: DetailsViewPagerListener) {
      bindingItem.nameViewPager.text = context.getStringByName(data.name)
//      bindingItem.endViewPager.text = context.getStringByName(data.end)
      val innerViewPager = bindingItem.mainViewPager2
      val innerAdapter = InnerDetailsCarouselAdapter(context, data.image)
      innerViewPager.adapter = innerAdapter

      val innerIndicator = bindingItem.innerIndicator
      innerIndicator.setViewPager(innerViewPager)
      bindingItem.next.setOnClickListener {
        val nextPage = innerViewPager.currentItem + 1
        if (nextPage < innerAdapter.itemCount) {
          innerViewPager.currentItem = nextPage
        }
      }


      bindingItem.previus.setOnClickListener {
        val nextPage = innerViewPager.currentItem - 1
        if (nextPage < innerAdapter.itemCount) {
          innerViewPager.currentItem = nextPage
        }
      }

    }
  }
}

class InnerDetailsCarouselAdapter(private var context: Context, private val innerItems: List<String>) : RecyclerView.Adapter<InnerDetailsCarouselAdapter.InnerViewHolder>() {

  override fun onCreateViewHolder(container: ViewGroup, viewType: Int): InnerViewHolder {
    return InnerViewHolder(
      ItemHomeViewPageInternoBinding.inflate(LayoutInflater.from(container.context), container, false)
    )
  }

  override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
    holder.bind(innerItems[position], context)
  }

  override fun getItemCount(): Int {
    return innerItems.size
  }

  class InnerViewHolder(private val itemHomeViewPageInternoBinding: ItemHomeViewPageInternoBinding) : RecyclerView.ViewHolder(itemHomeViewPageInternoBinding.root) {
    fun bind(data: String, context: Context) {
      Picasso.get().load(context.getDrawableByName(data)).into(itemHomeViewPageInternoBinding.imageViewPager)

    }
  }
}
