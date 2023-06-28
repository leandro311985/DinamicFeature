package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
          when (chip.text.toString()) {
            "Homen" -> logicDataType(1)
            "Mulher" -> logicDataType(2)
            "LGBTQIA+" -> logicDataType(3)
            "Homens e Mulheres" -> logicDataType(5)
            "Todos os gêneros" -> logicDataType(8)
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

  private fun logicDataType(id:Int){
    when (id) {
      1 -> {
        data.masculino = true
        data.feminino = false
        data.lgbtqa = false
      }
      2 -> {
        data.masculino = false
        data.lgbtqa = false
        data.feminino = true
      }
      3 ->{
        data.masculino = false
        data.feminino = false
        data.lgbtqa = true
      }
      5 ->{
        data.masculino = true
        data.feminino = true
        data.lgbtqa = false
      }
      8 ->{
        data.masculino = true
        data.feminino = true
        data.lgbtqa = true
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
              binding.chip2.isChecked = result.masculino?:false
              binding.chip3.isChecked = result.feminino?:false
              binding.chip4.isChecked = result.lgbtqa?:false
            }
          }
        }
      }
    }
  }
}