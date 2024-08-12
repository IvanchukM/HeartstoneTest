package com.example.heartstonetestapp.presentation.util

import com.example.heartstonetestapp.presentation.models.CardUI

sealed class CardsUIState {

  data object Loading : CardsUIState()

  data object Error : CardsUIState()

  class Success(val cards: List<CardUI>) : CardsUIState()
}
