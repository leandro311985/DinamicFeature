package com.example.dinamicfeature.presentation.searchSetting

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSearchSettingBinding

class SearchSettingFragment : BaseFragment(R.layout.fragment_search_setting) {

  private lateinit var binding: FragmentSearchSettingBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSearchSettingBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    containerToolbarSearchSetting.title.text = "Ajuste de busca"
    containerToolbarSearchSetting.backgraund.setBackgroundColor(resources.getColor(R.color.DEFAULT_GRAY_APP))

    containerToolbarSearchSetting.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }

    seekBarAjuste.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val height = (progress + 50).toString()
        km.text = "$height km"
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })

    seekBarAjusteFaixaetaria.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val height = (progress + 100).toString()
        faixaEtaria.text = "$height km"
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })

    seekBarAjustePhotos.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val height = (progress + 1).toString()
        faixaPhotos.text = "$height"
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })

  }
}