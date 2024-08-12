package com.example.heartstonetestapp.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.heartstonetestapp.data.LocalCardsRepository
import com.example.heartstonetestapp.presentation.models.CardUI
import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import com.example.heartstonetestapp.presentation.screens.SharedCardList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CardsDetailsViewModel @Inject constructor(
  private val localCardsRepository: LocalCardsRepository,
  sharedViewModel: CardsSharedViewModel,
) : ViewModel() {


  var cardsDetailsState: MutableState<List<CardUI>> = mutableStateOf(emptyList())
    private set

    init {
    setCardsDetailsState(sharedViewModel.items)
  }
  fun toggleFavouriteState(cardUI: CardUI) {
    if (cardUI.isFavourite) {
      removeCardFromFavourite(cardUI = cardUI)
    } else {
      saveCardToFavourite(cardUI = cardUI)
    }
  }

  fun setCardsDetailsState(sharedCardList: SharedCardList) {
    cardsDetailsState.value = sharedCardList.cards
  }

  private fun saveCardToFavourite(cardUI: CardUI) {
    viewModelScope.launch(Dispatchers.IO) {
      localCardsRepository.saveCardToFavourite(cardUI.cardId)
      withContext(Dispatchers.Main) {
        updateFavouriteList(cardUI = cardUI, isFavourite = true)
      }
    }
  }

  private fun removeCardFromFavourite(cardUI: CardUI) {
    viewModelScope.launch(Dispatchers.IO) {
      localCardsRepository.removeCardFromFavourite(cardUI.cardId)
      withContext(Dispatchers.Main) {
        updateFavouriteList(cardUI = cardUI, isFavourite = false)
      }
    }
  }

  private fun updateFavouriteList(cardUI: CardUI, isFavourite: Boolean) {
    cardsDetailsState.value = cardsDetailsState.value.map { card ->
      if (card == cardUI) {
        cardUI.copy(isFavourite = isFavourite)
      } else {
        card
      }
    }
  }
}

