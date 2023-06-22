package com.example.dinamicfeature.presentation.details.presentetion

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentDetailsBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.extension.getDrawableByName
import com.example.extension.getStringByName
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : BaseFragment(R.layout.fragment_details) {

  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private val viewModel: DetailsViewModel by viewModel()
  private var listFake = mutableListOf<PersonsFake?>()
  private var position = 0


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
    position = args.position
  }

  private fun getLists(){
    viewModel.getListPerson()
    viewModel.getPersonFake()
  }
  private fun setElements() = binding.apply {
    back.setOnClickListener {
     var copy =  listFake.toList()
      findNavController().navigateUp()

    }
    floatingActionButtonLike.setOnClickListener {
      if (it.isClickable){
        listFake[position]?.like = true
      }
    }
    floatingActionButtonCancel.setOnClickListener {
      if (it.isClickable){
        listFake[position]?.like = false
      }
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

        launch {
          viewModel.getPersonFake.collect { data ->
            binding.nameUser.text = data?.name?.let { context?.getStringByName(it) }
            context?.getDrawableByName(data?.image ?: "")?.let { Picasso.get().load(it).into(binding.imageViewPhoto) }
          }
        }

        launch {
          viewModel.listPerson.collect { list ->
            listFake = list.toMutableList()
          }
        }

      }
    }
  }

}