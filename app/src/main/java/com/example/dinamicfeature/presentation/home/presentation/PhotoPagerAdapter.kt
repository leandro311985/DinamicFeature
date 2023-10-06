package com.example.dinamicfeature.presentation.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.R
import com.example.dinamicfeature.databinding.ItemHomeOneViewPageBinding
import com.example.dinamicfeature.databinding.ItemHomeViewPageInternoBinding
import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.extension.createSnackbar
import com.example.extension.getDrawableByName
import com.example.extension.getStringByName
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class PhotoPagerAdapter(private var listener: HomeViewPagerListener,
                        private var listenerColapse: HomeColapseListener,
                        private var context: Context,
                        private val photos: List<PersonsFake>) :
  RecyclerView.Adapter<PhotoPagerAdapter.MainViewHolder>() {

  override fun onCreateViewHolder(container: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(
      ItemHomeOneViewPageBinding.inflate(LayoutInflater.from(container.context), container, false)
    )
  }

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    holder.bind(photos[position], context, listener, position,listenerColapse)
  }

  override fun getItemCount(): Int {
    return photos.size
  }

  class MainViewHolder(private val bindingItem: ItemHomeOneViewPageBinding) : RecyclerView.ViewHolder(bindingItem.root) {
    @SuppressLint("NotifyDataSetChanged")
    fun scrollToStart() {
      val recyclerView = itemView.parent as RecyclerView
      recyclerView.smoothScrollToPosition(0)
      recyclerView.adapter?.notifyDataSetChanged()
    }

    fun bind(data: PersonsFake, context: Context, listener: HomeViewPagerListener, position: Int,colapseListener: HomeColapseListener) {
       var isClicked = false
      bindingItem.nameViewPager.text = context.getStringByName(data.name)
      bindingItem.endViewPager.text = context.getStringByName(data.mural)
      val innerViewPager = bindingItem.mainViewPager2
      val innerAdapter = InnerCarouselAdapter(context, data.image)
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
      bindingItem.floatingActionButtonLike.setOnClickListener {
        val text = context.getString(R.string.snack_like, context.getStringByName(data.name))
        if (it.isClickable) {
          context.createSnackbar(
            bindingItem.floatingActionButtonLike,
            text,
            Snackbar.LENGTH_LONG
          )

          val list = MyPersonsFake(data, negative = false, likeTo = true, matchs = false, talvez = false, myLikes = false)
          listener.onItemClickListenerViewPager(data, list, true, position)

        }
      }

      bindingItem.gradientView.setOnClickListener {
        val list = MyPersonsFake(data, negative = false, likeTo = false, matchs = false, talvez = false, myLikes = false)
        listener.onItemClickListenerViewPager(data, list, false, position)
      }

      bindingItem.floatingActionTalvez.setOnClickListener {
        if (it.isClickable) {
          val text = context.getString(R.string.snack_talvez, context.getStringByName(data.name))

          context.createSnackbar(
            bindingItem.floatingActionButtonLike,
            text,
            Snackbar.LENGTH_LONG
          )
          val list = MyPersonsFake(data, negative = false, likeTo = false, matchs = false, talvez = true, myLikes = false)
          listener.onItemClickListenerViewPager(data, list, true, position)
        }
      }
      bindingItem.floatingActionButtonDislike.setOnClickListener {
        val nextPage = innerViewPager.currentItem + 1
        if (nextPage < innerAdapter.itemCount) {
          innerViewPager.currentItem = nextPage
        }
      }

      bindingItem.floatingActionButtonUpdate.setOnClickListener {
        if (it.isClickable) {
          scrollToStart()
        }
      }

      bindingItem.floatingActionButtonSeta.setOnClickListener {
        isClicked = !isClicked

        if (it.isClickable) {
           if (isClicked) {
            colapseListener.onItemClickListenerViewPager(true)
             bindingItem.floatingActionButtonSeta.setImageResource(R.drawable.seta_inversa)

           } else {
             colapseListener.onItemClickListenerViewPager(false)
             bindingItem.floatingActionButtonSeta.setImageResource(R.drawable.seta)

           }

        }
      }
    }
  }
}

class InnerCarouselAdapter(private var context: Context, private val innerItems: List<String>) : RecyclerView.Adapter<InnerCarouselAdapter.InnerViewHolder>() {

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
