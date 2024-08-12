package com.example.heartstonetestapp.presentation.util

import com.example.heartstonetestapp.presentation.models.CardUI

sealed class LoadingState {

  data object Loading : LoadingState()

  data object Error : LoadingState()

  data object Success : LoadingState()
}