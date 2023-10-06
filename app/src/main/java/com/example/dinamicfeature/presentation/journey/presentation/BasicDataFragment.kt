package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentBasicDataBinding
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.extension.countFilledFields
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
  private var selectedDateVar = ""
  private var country = ""
  private var countryDefault = ""


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
    getData()
    setElements()
    setCollectors()
  }

  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentBasicDataBinding.bind(view)
  }

  private fun getData() {
    viewModel.getRegisterUser()
  }

  private fun setElements() = binding.apply {
    btnSave.setOnClickListener {
      if (binding.editData.text.toString() != "") {
        data.selectedDate = selectedDateVar
      }
      data.city = binding.editCidade.text.toString()
      data.street = binding.editEstado.text.toString()
      data.nickname = binding.editName.text.toString()
      data.country = country
      val filledCount = countFilledFields(binding.editCidade.text.toString(), binding.editEstado.text.toString(), binding.editName.text.toString(),selectedDateVar,null)
      data.countFilledFields = filledCount
      viewModel.saveRegisterDb(data)
    }

    btnCancel.setOnClickListener {
      findNavController().navigateUp()
    }

    back.setOnClickListener {
      findNavController().navigateUp()
    }


    val spinnerCountries = editPais
    val countryNames = resources.getStringArray(R.array.country_names)
    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countryNames)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinnerCountries.adapter = adapter

    spinnerCountries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedCountry = countryNames[position]
        country = selectedCountry
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
        selectedDateVar = selectedDate.toString(getString(R.string.APP_DATE_PATTERN))
      }

      datePicker.addOnNegativeButtonClickListener {
        editData.setText("")
      }

      datePicker.show(requireActivity().supportFragmentManager, "ReminderDatePicker")
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
        launch {
          viewModel.getDataRegister.collect { result ->
            if (result != null) {
              if (result.nickname != null) binding.editName.setText(result.nickname.toString())
              binding.editData.setText(result.selectedDate)
              binding.editCidade.setText(result.city)
              binding.editEstado.setText(result.street)

              selectedDateVar = result.selectedDate ?: ""
              countryDefault = result.country.toString()


            }

          }
        }
      }
    }
  }

}