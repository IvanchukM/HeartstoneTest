package com.example.heartstonetestapp.presentation.main_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartstonetestapp.data.LocalCardsRepository
import com.example.heartstonetestapp.data.RemoteCardsRepository
import com.example.heartstonetestapp.data.util.RequestResult
import com.example.heartstonetestapp.presentation.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
  private val remoteCardsRepository: RemoteCardsRepository,
  private val localCardsRepository: LocalCardsRepository
) : ViewModel() {

  private val _initialLoadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
  val initialLoadingState: StateFlow<LoadingState> = _initialLoadingState

  fun loadCards() {
    viewModelScope.launch(Dispatchers.IO) {
      if (!localCardsRepository.getInitialLoadState()) {
        val result = remoteCardsRepository.loadCards()
        when (result) {
          RequestResult.Error -> {
            _initialLoadingState.value = LoadingState.Error
          }

          is RequestResult.Success -> {
            _initialLoadingState.value = LoadingState.Success
          }
        }
      } else {
        _initialLoadingState.value = LoadingState.Success
      }
    }
  }
}
