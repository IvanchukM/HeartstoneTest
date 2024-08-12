package com.example.heartstonetestapp.data.util

sealed class RequestResult<out T> {
  data class Success<out T>(val data: T) : RequestResult<T>()

  data object Error : RequestResult<Nothing>()
}
