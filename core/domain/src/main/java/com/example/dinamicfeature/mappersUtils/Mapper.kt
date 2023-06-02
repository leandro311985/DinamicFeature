package com.example.dinamicfeature.mappersUtils

interface Mapper<I, O> {
  fun map(input: I): O
}