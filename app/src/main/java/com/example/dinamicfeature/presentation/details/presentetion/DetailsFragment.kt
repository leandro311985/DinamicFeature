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
import kotlinx.coroutines.launch
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

//    binding.nameUser.text = personsFake.name.let { context?.getStringByName(it) }
//    context?.getDrawableByName(personsFake.image[0])?.let { Picasso.get().load(it).into(binding.imageViewPhoto) }
    back.setOnClickListener {
      findNavController().navigateUp()

    }
//    floatingActionButtonLike.setOnClickListener {
//      val text = getString(R.string.snack_like, context?.getStringByName(personsFake.name))
//      if (it.isClickable) {
//        requireContext().createSnackbar(
//          floatingActionButtonLike,
//          text,
//          Snackbar.LENGTH_LONG
//        )
//
//        val list = MyPersonsFake(personsFake, negative = false, likeTo = true, matchs = false, talvez = false, myLikes = false)
//        viewModel.saveLikeList(list)
//      }
//    }
//    floatingActionButtonCancel.setOnClickListener {
//      if (it.isClickable) {
//        val list = MyPersonsFake(personsFake, false, false, false, false, false)
//        requireContext().createSnackbar(
//          floatingActionButtonLike,
//          getText(R.string.snack_like).toString(),
//          Snackbar.LENGTH_LONG
//        )
//      }
//    }
//    floatingActionTalvez.setOnClickListener {
//      if (it.isClickable) {
//        val text = getString(R.string.snack_talvez, context?.getStringByName(personsFake.name))
//
//        val list = MyPersonsFake(personsFake, negative = false, likeTo = false, matchs = false, talvez = true, myLikes = false)
//        requireContext().createSnackbar(
//          floatingActionButtonLike,
//          text,
//          Snackbar.LENGTH_LONG
//        )
//        viewModel.saveLikeList(list)
//      }
//    }
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


            }
          }
        }

      }
    }
  }

  override fun onItemClickListenerViewPager(personsFake: PersonsFake, myPersonsFake: MyPersonsFake?, isFloatActionButtom: Boolean) {
    when (isFloatActionButtom) {
      true -> {
        myPersonsFake?.let { viewModel.saveLikeList(it) }
      }

      false -> {
        val nextPage = binding.viewPagePhoto.currentItem + 1
        if (nextPage < adapterDetail.itemCount) {
          binding.viewPagePhoto.currentItem = nextPage
        }
      }
    }
  }
}