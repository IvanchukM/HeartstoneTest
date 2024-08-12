package com.example.heartstonetestapp.presentation.util

sealed class LoadingState {

  data object Loading : LoadingState()

  data object Error : LoadingState()

  data object Success : LoadingState()
}