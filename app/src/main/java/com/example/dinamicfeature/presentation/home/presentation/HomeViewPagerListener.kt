package com.example.dinamicfeature.presentation.home.presentation

import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.models.PersonsFake

interface HomeViewPagerListener {
  fun onItemClickListenerViewPager(personsFake: PersonsFake?, myPersonsFake: MyPersonsFake?, isFloatActionButtom:Boolean?,position:Int)

}