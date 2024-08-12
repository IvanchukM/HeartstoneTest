package com.example.heartstonetestapp.presentation.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartstonetestapp.data.LocalCardsRepository
import com.example.heartstonetestapp.data.util.RequestResult
import com.example.heartstonetestapp.presentation.util.CardsUIState
import com.example.heartstonetestapp.presentation.util.Mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
  private val localCardsRepository: LocalCardsRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow<CardsUIState>(CardsUIState.Loading)
  val uiState: StateFlow<CardsUIState> = _uiState.asStateFlow()

  private var filterState by mutableStateOf(FilterState())

  fun getCardsByClass() {
    viewModelScope.launch(Dispatchers.IO) {
      localCardsRepository.getAllCards().collect { result ->
        when (result) {
          is RequestResult.Error -> {
            _uiState.value = CardsUIState.Error
          }

          is RequestResult.Success -> {
            _uiState.value = CardsUIState.Success(cards = result.data.map { it.toUiModel() })
          }
        }
      }
    }
  }

  fun sortCardsByClass() {
    val currentState = _uiState.value
    if (currentState is CardsUIState.Success) {
      filterState = filterState.copy(className = true)
      val sortedCards = currentState.cards.sortedWith(compareBy(nullsLast()) { it.playerClass })
      _uiState.value = CardsUIState.Success(cards = sortedCards)
    }
  }


  fun sortCardsAscending() {
    val currentState = _uiState.value
    if (currentState is CardsUIState.Success) {
      filterState = filterState.copy(costAsc = true)
      val sortedCards = currentState.cards.sortedWith(compareBy(nullsLast()) { it.cost })
      _uiState.value = CardsUIState.Success(cards = sortedCards)
    }
  }

  fun sortCardsDescending() {
    val currentState = _uiState.value
    if (currentState is CardsUIState.Success) {
      filterState = filterState.copy(costDesc = true)
      val sortedCards = currentState.cards.sortedByDescending { it.cost }
      _uiState.value = CardsUIState.Success(cards = sortedCards)
    }
  }

  data class FilterState(
    val costDesc: Boolean = false,
    val costAsc: Boolean = false,
    val className: Boolean = false,
  )
}
