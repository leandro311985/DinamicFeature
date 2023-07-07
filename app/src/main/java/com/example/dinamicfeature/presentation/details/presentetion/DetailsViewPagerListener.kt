package com.example.dinamicfeature.presentation.details.presentetion

import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.models.PersonsFake

interface DetailsViewPagerListener {
  fun onItemClickListenerViewPager(personsFake: PersonsFake, myPersonsFake: MyPersonsFake?, isFloatActionButtom:Boolean)

}