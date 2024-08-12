package com.example.heartstonetestapp.presentation.screens.favourite

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
class FavouriteCardsViewModel @Inject constructor(
  private val localCardsRepository: LocalCardsRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow<CardsUIState>(CardsUIState.Loading)
  val uiState: StateFlow<CardsUIState> = _uiState.asStateFlow()

  fun getFavouriteCards() {
    viewModelScope.launch(Dispatchers.IO) {
      localCardsRepository.getFavouriteCards().collect { result ->
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
}