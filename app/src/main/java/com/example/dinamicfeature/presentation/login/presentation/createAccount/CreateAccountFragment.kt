package com.example.dinamicfeature.presentation.login.presentation.createAccount

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountFragment : BaseFragment(R.layout.fragment_create_account) {

  private lateinit var binding: FragmentCreateAccountBinding
  private val viewModel: CreateAccountViewModel by viewModel()
  private lateinit var imageFlow: MutableStateFlow<String?>
  private var uri_Imagem: Uri? = null
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
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentCreateAccountBinding.bind(view)
    imageFlow = MutableStateFlow(null)
    auth = FirebaseAuth.getInstance()

  }

  private fun loading(isVisible:Boolean) = binding.apply{
    loadingContainer.isVisible = isVisible
  }


  private fun checkPermission() {
    if (ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.READ_EXTERNAL_STORAGE
      ) == PackageManager.PERMISSION_GRANTED
    ) {
      openGallery()
    } else {
      requestGalleryPermission()
    }
  }

  private fun requestGalleryPermission() {
    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
  }

  private fun openGallery() {
    getContentLauncher.launch("image/*")
  }

  private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { isGranted: Boolean ->
    if (isGranted) {
      openGallery()
    } else {
      // A permissão foi negada pelo usuário
      // Lide com isso de acordo com os requisitos do seu aplicativo
    }
  }

  private val getContentLauncher = registerForActivityResult(
    ActivityResultContracts.GetContent()
  ) { uri ->
    uri_Imagem = uri
    val imagePath = uri.toString()
    imageFlow.value = imagePath
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
      checkPermission()
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

    } else {
      if (!binding.emailText.text.toString().isValidEmail()) {
        isValid = false
        Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.invalid_password), Toast.LENGTH_SHORT).show()

      }
    }
    if (binding.passwordText.text.toString() != binding.passwordConfirmeEditText.text.toString()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.enter_password_check), Toast.LENGTH_SHORT).show()
    }
    if (binding.passwordText.text.isNullOrEmpty()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.enter_password), Toast.LENGTH_SHORT).show()

    } else {
      if (binding.passwordText.text.toString().length < 8) {
        isValid = false
        Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.password), Toast.LENGTH_SHORT).show()

      }
    }
    if (imageFlow.value == null) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.error_photo), Toast.LENGTH_SHORT).show()
      loading(false)
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
          imageFlow.collect { imagePath ->
            imagePath?.let { uri ->
              binding.logoImageView.setImageURI(Uri.parse(uri))

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
            viewModel.savePhoto(uri_Imagem,userData?.id?:"",{
              viewModel.savePhotoDb(it)
              savePhotoFirebase(userData?.id?:"",it.toString())
            },{
              Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
            })

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