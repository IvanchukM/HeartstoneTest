package com.example.heartstonetestapp.presentation.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.components.ScreenWrapper
import com.example.heartstonetestapp.presentation.models.CardUI
import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun CardDetailsRoute(
  onNavigateUp: () -> Unit,
  modifier: Modifier = Modifier,
  cardsSharedViewModel: CardsSharedViewModel,
  viewModel: CardsDetailsViewModel = hiltViewModel(),
) {
  val sharedCardList = cardsSharedViewModel.items

  val cardsDetailsState by viewModel.cardsDetailsState

  CardDetailsScreen(
    cardsList = cardsDetailsState,
    selectedCardIndex = sharedCardList.cardIndex,
    onNavigateUp = onNavigateUp,
    onFavouriteClicked = viewModel::toggleFavouriteState,
    modifier = modifier,
  )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CardDetailsScreen(
  cardsList: List<CardUI>,
  selectedCardIndex: Int,
  onFavouriteClicked: (CardUI) -> Unit,
  onNavigateUp: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState: ScrollState = rememberScrollState()
  val pagerState = rememberPagerState(
    initialPage = selectedCardIndex,
    pageCount = { cardsList.size }
  )

  ScreenWrapper(
    modifier = modifier,
    showTopAppBar = true,
    showNavigateUp = true,
    onNavigateUp = onNavigateUp,
    appBarTitle = R.string.app_bar_title_cards_details,
  ) {
    Column(
      modifier = Modifier
        .verticalScroll(scrollState)
    ) {
      HorizontalPager(
        state = pagerState,
        key = { page -> cardsList[page].cardId }
      ) { page ->
        val card = cardsList[page]
        CardDetailsSection(
          card = card,
          onFavouriteClicked = onFavouriteClicked
        )
      }
    }

  }
}

@Composable
private fun CardDetailsSection(
  card: CardUI,
  onFavouriteClicked: (CardUI) -> Unit,
  modifier: Modifier = Modifier
) {

  Column(
    modifier = modifier
      .padding(16.dp)
  ) {

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(0.75f)
    ) {
      SubcomposeAsyncImage(
        model = card.image,
        contentDescription = null,
        loading = {
          CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
          )
        },
        error = {
          Image(
            painter = painterResource(id = R.drawable.no_image_placeholder),
            contentDescription = null
          )
        },
        modifier = Modifier.fillMaxSize()
      )
      Icon(
        imageVector = if (card.isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = null,
        tint = if (card.isFavourite) Color.Red else Color.Gray,
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(8.dp)
          .clickable { onFavouriteClicked(card) }
      )
    }

    if (card.name.isNotBlank()) {
      Text(
        text = stringResource(
          R.string.card_name_template,
          card.name,
        )
      )

    }
    if (card.description?.isNotBlank() == true) {
      Text(
        text = stringResource(
          R.string.card_description_template,
          card.description,
        )
      )
    }
  }
}

/** Previews **/

@Composable
@ThemePreviews
private fun CardPreview() {
  ScreenThemeWrapper {
    CardDetailsSection(
      card = CardUI(
        cardId = "GVG_020",
        name = "Fel Cannon",
        cost = 4,
        description = "At the end of your turn, deal 2 damage to a non-Mech minion.",
        playerClass = "Warlock",
        image = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/0d38ac06d6dc9828857478f53f3cc48b5e69f3e625971f9767d7b05d43f4329e.png",
        isFavourite = false
      ),
      onFavouriteClicked = { },
    )
  }
}

@Composable
@ThemePreviews
private fun CardFavouritePreview() {
  ScreenThemeWrapper {
    CardDetailsSection(
      card = CardUI(
        cardId = "GVG_020",
        name = "Fel Cannon",
        cost = 4,
        description = "At the end of your turn, deal 2 damage to a non-Mech minion.",
        playerClass = "Warlock",
        image = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/0d38ac06d6dc9828857478f53f3cc48b5e69f3e625971f9767d7b05d43f4329e.png",
        isFavourite = true
      ),
      onFavouriteClicked = { },
    )
  }
}
