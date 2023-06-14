package com.example.dinamicfeature.presentation.register.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentPhysicalProfileBinding
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhysicalProfileFragment : BaseFragment(R.layout.fragment_physical_profile) {

  private lateinit var binding: FragmentPhysicalProfileBinding
  private lateinit var seekBar: SeekBar
  private lateinit var heightTextView: AppCompatTextView
  private val viewModel: RegisterViewModel by sharedViewModel()
  private lateinit var data: ProfileBasicDataUsers


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    data = ProfileBasicDataUsers()
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
    val thumbColor = Color.parseColor("#20304C")
    val progressColor = Color.parseColor("#20304C")
    seekBar.thumbTintList = ColorStateList.valueOf(thumbColor)
    seekBar.progressTintList = ColorStateList.valueOf(progressColor)

    heightTextView = binding.heightTextView

    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val height = (progress + 100).toString()
        heightTextView.text = "Sua altura $height cm"
        data.physical?.height = height
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })

    binding.chipGroupFilter.setOnCheckedChangeListener { group, checkedId ->
      val chip: Chip? = group.findViewById(checkedId)
      chip?.let {chipView ->
        Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
      } ?: kotlin.run {
      }
    }

  }
}