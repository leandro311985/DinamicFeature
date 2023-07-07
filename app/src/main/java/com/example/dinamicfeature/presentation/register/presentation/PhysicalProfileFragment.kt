package com.example.dinamicfeature.presentation.register.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentPhysicalProfileBinding
import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.extension.countFilledFields
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhysicalProfileFragment : BaseFragment(R.layout.fragment_physical_profile) {

  private lateinit var binding: FragmentPhysicalProfileBinding
  private lateinit var seekBar: SeekBar
  private lateinit var heightTextView: AppCompatTextView
  private val viewModel: RegisterViewModel by sharedViewModel()
  private lateinit var data: PhysicalData
  private var listAppearance = mutableListOf<String>()
  private var listBodyType = mutableListOf<String>()
  private var varHeight = ""



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    getData()
    data = PhysicalData()
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentPhysicalProfileBinding.bind(view)

  }
  private fun getData() {
    viewModel.getRegisterUserPhysicalData()
  }
  private fun setElements() {
    binding.btnSave.setOnClickListener {
      data.yourAppearance = listAppearance
      data.bodyType = listBodyType
      data.height = varHeight
      val filledCount = countFilledFields(heightTextView.text.toString(),null,null,null,null)
      data.countFilledFields = filledCount
      viewModel.saveRegisterDbPhysicalData(data)
    }

    binding.btnCancel.setOnClickListener {
      findNavController().navigateUp()
    }

    binding.back.setOnClickListener {
      findNavController().navigateUp()
    }

    seekBar = binding.seekBar
    val thumbColor = Color.parseColor("#20304C")
    val progressColor = Color.parseColor("#20304C")
    seekBar.thumbTintList = ColorStateList.valueOf(thumbColor)
    seekBar.progressTintList = ColorStateList.valueOf(progressColor)

    heightTextView = binding.heightTextView

    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val height = (progress + 100).toString()
        heightTextView.text = "Sua altura $height cm"
        varHeight = height
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })


    binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedId ->
      checkedId.map {
        val chip: Chip? = group.findViewById(it)
        if (chip?.isChecked == true) {
          listBodyType.add(chip.text.toString())
        } else {
          listBodyType.remove(chip?.text.toString())

        }
      }

    }

    binding.chipGroupFilter2.setOnCheckedStateChangeListener { group, checkedId ->
      checkedId.map {
        val chip: Chip? = group.findViewById(it)
        if (chip?.isChecked == true) {
          listAppearance.add(chip.text.toString())
        } else {
          listAppearance.remove(chip?.text.toString())
        }
      }
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.successPhysical.collect { result ->
            if (result) findNavController().navigateUp()
          }
        }
        launch {
          viewModel.getDataPhysicalData.collect { result ->
            if (result != null) {
              if (result.height != "") binding.heightTextView.text = "Sua altura ${result.height} cm"

            }
          }
        }
      }
    }
  }
}