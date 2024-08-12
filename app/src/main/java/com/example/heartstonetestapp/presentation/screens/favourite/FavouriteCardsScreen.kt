package com.example.heartstonetestapp.presentation.screens.favourite

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.components.CardSection
import com.example.heartstonetestapp.presentation.components.ErrorScreen
import com.example.heartstonetestapp.presentation.components.LoadingScreen
import com.example.heartstonetestapp.presentation.components.ScreenWrapper
import com.example.heartstonetestapp.presentation.models.CardUI
import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import com.example.heartstonetestapp.presentation.util.CardsUIState
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun FavouriteCardsRoute(
  onCardClicked: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: FavouriteCardsViewModel = hiltViewModel(),
  cardsSharedViewModel: CardsSharedViewModel,
) {
  val cardsState by viewModel.uiState.collectAsStateWithLifecycle()

  LaunchedEffect(Unit) {
    viewModel.getFavouriteCards()
  }

  when (cardsState) {
    CardsUIState.Error -> {
      ErrorScreen(
        showTopAppBar = true,
        appBarTitle = R.string.app_bar_title_favourite_cards,
        errorMessage = R.string.general_error_message
      )
    }

    CardsUIState.Loading -> {
      LoadingScreen(
        showTopAppBar = true,
        appBarTitle = R.string.app_bar_title_favourite_cards,
      )
    }

    is CardsUIState.Success -> {
      val successState = cardsState as CardsUIState.Success
      FavouriteCardsScreen(
        cards = successState.cards,
        onCardClicked = { index ->
          cardsSharedViewModel.setCardsList(successState.cards, index)
          onCardClicked()
        },
        modifier = modifier
      )
    }
  }
}

@Composable
private fun FavouriteCardsScreen(
  cards: List<CardUI>,
  onCardClicked: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  ScreenWrapper(
    modifier = modifier,
    showTopAppBar = true,
    appBarTitle = R.string.app_bar_title_favourite_cards,
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      itemsIndexed(cards) { index, card ->
        key(card.cardId) {
          CardSection(
            card = card,
            onCardClicked = {
              onCardClicked(index)
            }
          )
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
    FavouriteCardsScreen(
      cards = listOf(cardMock, cardMock),
      onCardClicked = { }
    )
  }
}