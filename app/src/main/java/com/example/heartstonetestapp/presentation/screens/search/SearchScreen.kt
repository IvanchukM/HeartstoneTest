package com.example.heartstonetestapp.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.components.CardSection
import com.example.heartstonetestapp.presentation.components.ScreenWrapper
import com.example.heartstonetestapp.presentation.components.SearchBar
import com.example.heartstonetestapp.presentation.models.CardUI
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun SearchRoute(
  onNavigateUp: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SearchViewModel = hiltViewModel(),
) {

  val searchTerm by viewModel.searchTerm.collectAsStateWithLifecycle()
  val searchState by viewModel.searchState.collectAsStateWithLifecycle()
  val isSearching by viewModel.isSearching

  SearchScreen(
    cards = searchState,
    searchTerm = searchTerm,
    onSearchTermChanged = viewModel::onSearchTextChange,
    onNavigateUp = onNavigateUp,
    isSearching = isSearching,
    modifier = modifier,
  )


}

@Composable
private fun SearchScreen(
  cards: List<CardUI>,
  searchTerm: String,
  isSearching: Boolean,
  onSearchTermChanged: (String) -> Unit,
  onNavigateUp: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val focusRequester = remember { FocusRequester() }

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }

  ScreenWrapper(
    modifier = modifier,
    showTopAppBar = true,
    showNavigateUp = true,
    onNavigateUp = onNavigateUp,
    appBarTitle = R.string.app_bar_title_search,
  ) {
    SearchBar(
      searchTerm = searchTerm,
      onSearchTermChanged = onSearchTermChanged,
      modifier = Modifier
        .focusRequester(focusRequester)
    )
    if (isSearching) {
      CircularProgressIndicator(
        modifier = Modifier
          .width(48.dp)
          .padding(vertical = 16.dp)
          .align(Alignment.CenterHorizontally),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
      )
    } else if (cards.isEmpty() && searchTerm.isNotBlank()) {
      Text(
        text = stringResource(id = R.string.search_no_cards_found), modifier = Modifier
          .padding(top = 32.dp)
          .align(Alignment.CenterHorizontally)
      )
    } else {
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
      ) {
        items(cards) { card ->
          key(card.cardId) {
            CardSection(
              card = card,
            )
          }
        }
      }
    }

  }
}


/** Previews **/

val cardMock = CardUI(
  cardId = "GVG_020",
  name = "Fel Cannon",
  cost = 4,
  description = "At the end of your turn, deal 2 damage to a non-Mech minion.",
  playerClass = "Warlock",
  image = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/0d38ac06d6dc9828857478f53f3cc48b5e69f3e625971f9767d7b05d43f4329e.png",
  isFavourite = false
)

@Composable
@ThemePreviews
private fun SearchScreenPreview() {
  ScreenThemeWrapper {
    SearchScreen(
      cards = listOf(cardMock, cardMock),
      onSearchTermChanged = { },
      isSearching = false,
      searchTerm = "",
      onNavigateUp = { },
    )
  }
}

@Composable
@ThemePreviews
private fun SearchLoadingScreenPreview() {
  ScreenThemeWrapper {
    SearchScreen(
      cards = listOf(cardMock, cardMock),
      onSearchTermChanged = { },
      isSearching = true,
      searchTerm = "",
      onNavigateUp = { },
    )
  }
}

@Composable
@ThemePreviews
private fun SearchEmptyScreenPreview() {
  ScreenThemeWrapper {
    SearchScreen(
      cards = emptyList(),
      onSearchTermChanged = { },
      isSearching = false,
      searchTerm = "123",
      onNavigateUp = { },
    )
  }
}