package com.example.dinamicfeature.presentation.home.presentation

import com.example.dinamicfeature.domain.models.PersonsFake

interface HomeListener {
  fun onItemClick(personsFake: PersonsFake, positionInt: Int)

}