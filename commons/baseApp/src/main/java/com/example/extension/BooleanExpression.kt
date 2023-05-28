package com.example.extension


import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun Boolean?.isTrue(): Boolean {
  contract {
    returns(true) implies (this@isTrue != null)
  }
  return this == true
}

@OptIn(ExperimentalContracts::class)
fun Boolean?.isFalse(): Boolean {
  contract {
    returns(true) implies (this@isFalse != null)
  }
  return this == false
}

val Boolean?.orTrue: Boolean
  get() = this ?: true

val Boolean?.orFalse: Boolean
  get() = this ?: false

//usage
//lateinit var any: Boolean? // Assume that, this property is already assigned
//if (any.isTrue()) {
//  // Run when any is true only
//}
//if (any.isFalse()) {
//  // Run when any is false only
//}
//val any1: Boolean = any.orTrue // If any is null then any1 = true otherwise any1 = any
//val any2: Boolean = any.orFalse // If any is null then any1 = false otherwise any1 = any