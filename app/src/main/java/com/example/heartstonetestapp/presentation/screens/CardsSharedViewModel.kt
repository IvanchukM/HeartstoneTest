package com.example.heartstonetestapp.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.heartstonetestapp.presentation.models.CardUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsSharedViewModel @Inject constructor() : ViewModel() {

  var items: SharedCardList by mutableStateOf(SharedCardList())
    private set

  fun setCardsList(cardsList: List<CardUI>, cardIndex: Int) {
    items = items.copy(
      cards = cardsList,
      cardIndex = cardIndex
    )
  }
}

data class SharedCardList(
  val cards: List<CardUI> = emptyList(),
  val cardIndex: Int = 0,
)
