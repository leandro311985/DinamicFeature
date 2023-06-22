package com.example.dinamicfeature.presentation.searchpeople.adapter

import com.example.dinamicfeature.domain.models.PersonsFake


interface CardListener {
  fun onItemClick(personsFake: PersonsFake,positionInt: Int)

}