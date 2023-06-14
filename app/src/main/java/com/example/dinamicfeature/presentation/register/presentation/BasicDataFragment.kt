package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentBasicDataBinding
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.extension.hideKeyboard
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BasicDataFragment : BaseFragment(R.layout.fragment_basic_data) {

  private lateinit var binding: FragmentBasicDataBinding
  private val viewModel: RegisterViewModel by sharedViewModel()
  private var data = ProfileBasicDataUsers()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
    setElements()
    setCollectors()
  }

  override fun initView(view: View) {
    setViewBinding(view)
    binding.back.setOnClickListener {
      viewModel.saveRegisterDb(data)
    }
  }

  override fun setViewBinding(view: View) {
    binding = FragmentBasicDataBinding.bind(view)

  }

  private fun setElements() = binding.apply {
    val nome = editName.text.toString()
    val selectedDateText = editData.text.toString()
    data.nickname = nome

    val spinnerCountries = editPais
    val countryNames = resources.getStringArray(R.array.country_names)
    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countryNames)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinnerCountries.adapter = adapter

    spinnerCountries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedCountry = countryNames[position]
        data.country = selectedCountry
      }

      override fun onNothingSelected(parent: AdapterView<*>?) {
        // Nenhuma seleção feita
      }
    }


    editData.setOnFocusChangeListener { v, hasFocus ->
      if (!hasFocus) return@setOnFocusChangeListener

      requireActivity().hideKeyboard(v)
      val builder = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Qual a data de nascimento?")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
      val datePicker = builder.build()

      datePicker.addOnPositiveButtonClickListener {
        val selectedDate = LocalDate(it, DateTimeZone.UTC)
        editData.setText(selectedDate.toString(getString(R.string.APP_DATE_PATTERN)))
        data.selectedDate = selectedDate.toString(getString(R.string.APP_DATE_PATTERN))
      }

      datePicker.addOnNegativeButtonClickListener {
        editData.setText("")
      }

      datePicker.show(requireActivity().supportFragmentManager, "ReminderDatePicker")
    }

    if (nome.isEmpty() || selectedDateText.isEmpty()) {
      // Um ou mais campos estão vazios
      // Realize ações apropriadas, como exibir uma mensagem de erro
    } else {
      // Todos os campos estão preenchidos
      // Continue com o processamento dos dados
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.success.collect { result ->
            if (result) findNavController().navigateUp()

          }
        }
      }
    }
  }

}