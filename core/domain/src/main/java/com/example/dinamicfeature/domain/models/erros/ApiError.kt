package com.example.dinamicfeature.domain.models.erros

class ApiError(
  val type: String,
  val title: String,
  var status: String,
  val traceId: String,
  var detail: String? = null,
  var userId: String? = null
) {

  @Transient
  var errors: List<String>? = null

  companion object {
    fun createApiError(): ApiError =
      ApiError(
        "",
        "",
        "",
        "",
        "",
        null
      )

  }
}