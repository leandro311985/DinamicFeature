package com.example.dinamicfeature.createAccount

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.baseApp.constants.Constants.DATA_USER
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.SavePhotoUseCase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CreateAccountViewModel(
  private val createUserUseCase: CreateUserUseCase,
  private val savePhotoUseCase: SavePhotoUseCase,
  private val getDataUseCase: GetDataUseCase
) : BaseViewModel() {

  private lateinit var firebaseStorage: FirebaseStorage
  private val _register = MutableSharedFlow<UiState<String>>()
  val register = _register.asSharedFlow()

  private val _success = MutableSharedFlow<Boolean>()
  val success = _success.asSharedFlow()

  private val _successDbFirebase = MutableSharedFlow<Boolean>()
  val successDbFirebase = _successDbFirebase.asSharedFlow()

  private val _error = MutableSharedFlow<Exception>()
  val error = _error.asSharedFlow()

  private val _userData = MutableSharedFlow<UserFirebase>()
  val userData = _userData.asSharedFlow()

  var state: Boolean? = null

  fun createAccount(email: String, password: String, name: String) {
    viewModelScope.launch {
      val result = createUserUseCase(
        email = email,
        password = password,
        user = UserFirebase(),
        name = name
      )

      delay(1000)
      _register.emit(result)
    }
  }

  fun savePhoto(uriFile: Uri?, fileName: String, success: (Uri) -> Unit, error: (Exception?) -> Unit) {
    viewModelScope.launch {
      firebaseStorage = FirebaseStorage.getInstance()
      val ref = firebaseStorage.getReference(fileName)
      val task = uriFile?.let { ref.putFile(it) }
      generateUrlDownload(ref, task, success, error)
    }
  }

  private fun generateUrlDownload(
    reference: StorageReference,
    task: StorageTask<UploadTask.TaskSnapshot>?,
    success: (uri: Uri) -> Unit,
    error: (Exception?) -> Unit
  ) {
    task?.continueWithTask { taskExecuted ->
      if (taskExecuted.isSuccessful) {

        reference.downloadUrl

      } else {
        taskExecuted.exception?.let {
          throw it
        }
      }
    }?.addOnCompleteListener { task ->
      if (task.isSuccessful) {
        task.result?.let(success) ?: run {
          error(Throwable("Unknown Error"))
        }
      } else {
        error(Throwable("Unknown Error!"))
      }
    }?.addOnFailureListener(error)
  }

  fun getDataUser() {
    viewModelScope.launch {
      delay(2000)
      val result = getDataUseCase(DATA_USER)
      if (result != null) {
        _userData.emit(result)
      }
    }
  }

  fun savePhotoDb(url: Uri) {
    viewModelScope.launch {
      val result = savePhotoUseCase(url)
      _success.emit(result)
    }
  }

}