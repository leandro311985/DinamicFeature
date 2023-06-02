package com.example.dinamicfeature.mappersUtils


class ListMapper<I, O>
constructor(
  private val mapper: Mapper<I, O>
) : IListMapper<I, O> {

  override fun map(input: List<I>): List<O> =
    input.map { mapper.map(it) }
}