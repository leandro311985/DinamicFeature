package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentGeralProfileBinding
import com.example.dinamicfeature.domain.models.ProfileGeneralData
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileGeneralFragment : BaseFragment(R.layout.fragment_geral_profile) {

  private lateinit var binding: FragmentGeralProfileBinding
  private val viewModel: RegisterViewModel by sharedViewModel()
  private var data = ProfileGeneralData()
  private var listObjective = mutableListOf<String>()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    getData()
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentGeralProfileBinding.bind(view)

  }

  private fun getData() {
    viewModel.getRegisterUserGeneralData()
  }

  private fun setElements() {
    binding.btnSave.setOnClickListener {
      saveData()
    }

    binding.btnCancel.setOnClickListener {
      findNavController().navigateUp()
    }

   binding.back.setOnClickListener {
      findNavController().navigateUp()
    }


    binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedId ->
      checkedId.map {
        val chip: Chip? = group.findViewById(it)
        if (chip?.isChecked == true) {
          data.countFilledFields = 2
          when (chip.text.toString()) {
            "Homen" -> data.type = "man"
            "Mulher" -> data.type = "femi"
            "LGBTQIA+" -> data.type = "lgbtqa"
            "Homens e Mulheres" -> data.type = "hm"
            "Todos os gêneros" -> data.type = "todos"
          }
        }
      }
    }


    binding.chipGroupFilter2.setOnCheckedStateChangeListener { group, checkedId ->
      checkedId.map {
        val chip: Chip? = group.findViewById(it)
        if (chip?.isChecked == true) {
          listObjective.add(chip.text.toString())
        } else {
          listObjective.remove(chip?.text.toString())
        }
      }
    }
  }


  private fun saveData() {

    viewModel.saveRegisterDbProfileGeneralData(data)
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.successGeneralData.collect { result ->
            if (result) findNavController().navigateUp()

          }
        }

        launch {
          viewModel.getDataProfileGeneralData.collect { result ->
            if (result != null) {
              when(result.type){
                "man"->  binding.chip2.isChecked =true
                "femi"-> binding.chip3.isChecked = true
                "lgbtqa"-> binding.chip4.isChecked = true
              }
            }
          }
        }
      }
    }
  }
}