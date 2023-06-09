package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentPhysicalProfileBinding

class PhysicalProfileFragment : BaseFragment(R.layout.fragment_physical_profile) {

  private lateinit var binding: FragmentPhysicalProfileBinding
  private lateinit var seekBar: SeekBar
  private lateinit var heightTextView: AppCompatTextView


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentPhysicalProfileBinding.bind(view)

  }

  private fun setElements() {
    binding.back.setOnClickListener {
      findNavController().navigateUp()
    }
    seekBar = binding.seekBar
    heightTextView = binding.heightTextView

    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val height = (progress + 100).toString()
        heightTextView.text = "Sua altura $height cm"
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })

    binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedId ->
      Toast.makeText(context, checkedId.toString(), Toast.LENGTH_SHORT).show()
    }

    binding.chipGroupFilter2.setOnCheckedStateChangeListener { group, checkedId ->
      val chip: MutableList<Int> = group.checkedChipIds
      chip?.let { chipView ->
        Toast.makeText(context, chip.toString(), Toast.LENGTH_SHORT).show()
      } ?: kotlin.run {
      }
    }
  }
}