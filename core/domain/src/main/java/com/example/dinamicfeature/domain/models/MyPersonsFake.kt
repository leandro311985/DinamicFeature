package com.example.dinamicfeature.domain.models

import java.io.Serializable

data class MyPersonsFake(
  var personsFake: PersonsFake,
  var negative:Boolean,
  var likeTo:Boolean,
  var matchs:Boolean,
  var talvez:Boolean,
  var myLikes:Boolean
):Serializable
