package com.example.dinamicfeature.presentation.login.presentation.createAccount

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.baseApp.commons.isValidEmail
import com.example.dinamicfeature.databinding.FragmentCreateAccountBinding
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.extension.createAlertDialogOneButton
import com.example.extension.hideKeyboard
import com.example.extension.isNetworkAvailable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountFragment : BaseFragment(R.layout.fragment_create_account) {

  private lateinit var binding: FragmentCreateAccountBinding
  private val viewModel: CreateAccountViewModel by viewModel()

  private var userData: UserFirebase? = null
  private var auth: FirebaseAuth? = null
  private lateinit var database: DatabaseReference

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    register()
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentCreateAccountBinding.bind(view)
    auth = FirebaseAuth.getInstance()

  }

  private fun setElements() = binding.apply {
    containerToolbar.title.text = "Criar conta"
    containerToolbar.title.setTextColor(ContextCompat.getColor(requireContext(), R.color.DEFAULT_WHITE))
    containerToolbar.backgraund.setBackgroundColor(resources.getColor(R.color.DEFAULT_AZUL_APP_4))
    containerToolbar.img.setImageResource(R.drawable.baseline_white_arrow_back_ios_24)
    containerToolbar.img2.isVisible = false
    containerToolbar.img.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun loading(isVisible: Boolean) = binding.apply {
    loadingContainer.loadingContainer.isVisible = isVisible
    container.isVisible = !isVisible

  }


  private fun register() = binding.apply {
    registerButton.setOnClickListener {
      loading(true)
      if (isNetworkAvailable()) {
        call(it)
      } else {
        showDialog()
      }

    }

    descriptionImageView.setOnClickListener {
      findNavController().navigate(CreateAccountFragmentDirections.actionCreateFragmentEditPhoto())
    }

    backTextView.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun call(view: View) = binding.apply {
    if (validation()) viewModel.createAccount(emailText.text.toString(), passwordText.text.toString(), nameText.text.toString())
    context?.hideKeyboard(view)
  }

  private fun showDialog() {
    requireContext().createAlertDialogOneButton(
      getString(com.example.dinamicfeature.R.string.app_name),
      getString(R.string.PROVISIONING_WIFI_MODAL_TITLE),
      getString(R.string.PROVISIONING_WIFI_MODAL_EXPLANATION)

    ) {
      loading(false)

    }
  }

  private fun validation(): Boolean {
    var isValid = true

    if (binding.nameText.text.isNullOrEmpty()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.name_empty), Toast.LENGTH_SHORT).show()
      loading(false)
    } else {
      if (!binding.emailText.text.toString().isValidEmail()) {
        isValid = false
        Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.invalid_password), Toast.LENGTH_SHORT).show()
        loading(false)
      }
    }
    if (binding.passwordText.text.toString() != binding.passwordConfirmeEditText.text.toString()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.enter_password_check), Toast.LENGTH_SHORT).show()
      loading(false)
    }
    if (binding.passwordText.text.isNullOrEmpty()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.enter_password), Toast.LENGTH_SHORT).show()
      loading(false)
    } else {
      if (binding.passwordText.text.toString().length < 8) {
        isValid = false
        Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.password), Toast.LENGTH_SHORT).show()
        loading(false)
      }
    }

    return isValid
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.register.collect { result ->
            when (result) {
              is UiState.Loading -> {

              }

              is UiState.Failure -> {

              }

              is UiState.Success -> {
                viewModel.getDataUser()
              }
            }
          }
        }

        launch {
          viewModel.success.collect { imagePath ->
            loading(false)
            findNavController().navigate(R.id.action_create_login_fragment_to_success)
          }
        }
        launch {
          viewModel.error.collect { error ->
            loading(false)
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
          }
        }

        launch {
          viewModel.userData.collect { dataUser ->
            userData = dataUser
            findNavController().navigate(R.id.action_create_login_fragment_to_success)

          }
        }
      }
    }
  }

  private fun savePhotoFirebase(userId: String, photo: String) {
    database = Firebase.database.reference
    val result = database.child("users").child(userId).setValue(photo).addOnCompleteListener {
      it.isSuccessful
      loading(false)

    }
    Toast.makeText(requireContext(), result.isSuccessful.toString(), Toast.LENGTH_SHORT).show()

  }

}