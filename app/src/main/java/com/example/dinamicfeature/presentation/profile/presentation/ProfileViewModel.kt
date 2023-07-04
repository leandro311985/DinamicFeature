package com.example.dinamicfeature.presentation.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.constants.Constants.DATA_USER
import com.example.dinamicfeature.baseApp.constants.Constants.SAVE_PHOTO
import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.main.GetLocationUseCase
import com.example.dinamicfeature.domain.useCases.main.GetMyListUseCase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPhotoUseCase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
  private val getDataUseCase: GetDataUseCase,
  private val getPhotoUseCase: GetPhotoUseCase,
  private val getLocationUseCase: GetLocationUseCase,
  private val getMyListUseCase: GetMyListUseCase
) : BaseViewModel() {

  private val _user = MutableSharedFlow<UserFirebase>()
  val user = _user.asSharedFlow()

  private val _photoUser = MutableSharedFlow<String>()
  val photoUser = _photoUser.asSharedFlow()

  private val _location = MutableSharedFlow<LocationData>()
  val location = _location.asSharedFlow()

  private val _myListLike = MutableSharedFlow<Int>()
  val myListLikeSize = _myListLike.asSharedFlow()

  private val _myListHomeTalvez = MutableSharedFlow<Int>()
  val myListHomeTalvez = _myListHomeTalvez.asSharedFlow()


  fun getDataUser() {
    viewModelScope.launch {
      val result = getDataUseCase(DATA_USER)
      delay(500)
      if (result != null) {
        _user.emit(result)
      }
    }
  }

  fun getPhoto() {
    viewModelScope.launch {
      val result = getPhotoUseCase(SAVE_PHOTO)
      _photoUser.emit(result ?: "")
    }
  }

  fun getLocation(){
    viewModelScope.launch {
      delay(2000)
      val result = getLocationUseCase()
      _location.emit(result)
    }
  }

  fun getMyList() {
    viewModelScope.launch {
      val result = getMyListUseCase()
      delay(500)
      val list1 = result.filter { it?.likeTo == true }
      val size = list1.size
      _myListLike.emit(size)
      val list2 = result.filter { it?.talvez == true }
      val sizeTalvez = list2.size
      _myListHomeTalvez.emit(sizeTalvez)
    }
  }
}