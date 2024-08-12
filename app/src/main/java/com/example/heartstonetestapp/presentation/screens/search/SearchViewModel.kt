package com.example.heartstonetestapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartstonetestapp.data.RemoteCardsRepository
import com.example.heartstonetestapp.data.util.RequestResult
import com.example.heartstonetestapp.presentation.models.CardUI
import com.example.heartstonetestapp.presentation.util.Mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchViewModel @Inject constructor(
  private val remoteCardsRepository: RemoteCardsRepository
) : ViewModel() {

  var searchTerm = MutableStateFlow("")
    private set

  var isSearching = mutableStateOf(false)
    private set

  val searchState: StateFlow<List<CardUI>> = searchTerm
    .debounce(1_000L)
    .flatMapLatest { term ->
      if (term.isNotBlank()) {
        remoteCardsRepository.getCardByName(term)
          .map { result ->
            when (result) {
              is RequestResult.Success -> {
                isSearching.value = false
                result.data.map { it.toUiModel() }
              }

              is RequestResult.Error -> {
                isSearching.value = false
                emptyList()
              }
            }
          }
      } else {
        flowOf(emptyList())
      }
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.Eagerly,
      initialValue = emptyList()
    )

  fun onSearchTextChange(searchTerm: String) {
    this.searchTerm.tryEmit(searchTerm)
    isSearching.value = searchTerm.isNotBlank()
  }
}
