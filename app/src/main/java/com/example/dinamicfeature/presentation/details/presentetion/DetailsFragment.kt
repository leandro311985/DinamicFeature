package com.example.dinamicfeature.presentation.details.presentetion

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentDetailsBinding
import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.models.PersonsFake
import android.graphics.Bitmap
import android.graphics.Matrix
import com.example.extension.getStringByName
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : BaseFragment(R.layout.fragment_details), DetailsViewPagerListener {

  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private val viewModel: DetailsViewModel by viewModel()
  private lateinit var adapterDetail: PhotoDetailsPagerAdapter
  private var personsFake: Int = 0


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    getLists()
    checkArgs()
    setCollectors()
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentDetailsBinding.bind(view)
  }

  private fun checkArgs() {
    personsFake = args.position
  }

  private fun getLists() {
    viewModel.getListPerson()
    viewModel.getPersonFake()
  }

  private fun setElements() = binding.apply {

    back.setOnClickListener {
      findNavController().navigateUp()
    }

    seta.setOnClickListener {
      if (details.visibility == View.VISIBLE) {
        details.visibility = View.GONE
        seta.setImageResource(R.drawable.seta)
      } else {
        details.visibility = View.VISIBLE
        seta.setImageResource(R.drawable.seta_inversa)
      }
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

        launch {
          viewModel.getPersonFake.collect { data ->
            binding.viewPagePhoto.apply {
              adapterDetail = PhotoDetailsPagerAdapter(this@DetailsFragment, requireContext(), data)
              adapter = adapterDetail
              val desiredPosition = personsFake
              currentItem = desiredPosition
              isUserInputEnabled = false

              binding.nameUser.text = context.getStringByName(data[personsFake].name)

            }
            val list = mutableListOf<CarouselItem>()
            list.add(CarouselItem(R.drawable.lugares1))
            list.add(CarouselItem(R.drawable.lugares2))
            list.add(CarouselItem(R.drawable.lugares3))
            list.add(CarouselItem(R.drawable.lugares4))
            list.add(CarouselItem(R.drawable.lugares5))
            binding.imageCorresel.registerLifecycle(lifecycle)
            binding.imageCorresel.setData(list)
          }
        }

      }
    }
  }

  override fun onItemClickListenerViewPager(personsFake: PersonsFake, myPersonsFake: MyPersonsFake?, isFloatActionButtom: Boolean) {
//    when (isFloatActionButtom) {
//      true -> {
//        myPersonsFake?.let { viewModel.saveLikeList(it) }
//      }
//
//      false -> {
//        val nextPage = binding.viewPagePhoto.currentItem + 1
//        if (nextPage < adapterDetail.itemCount) {
//          binding.viewPagePhoto.currentItem = nextPage
//        }
//      }
//    }
  }
}