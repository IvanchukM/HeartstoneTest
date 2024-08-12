package com.example.heartstonetestapp.presentation.screens.list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.data.worker.CardUpdateWorker
import com.example.heartstonetestapp.presentation.components.CardSection
import com.example.heartstonetestapp.presentation.components.ErrorScreen
import com.example.heartstonetestapp.presentation.components.LoadingScreen
import com.example.heartstonetestapp.presentation.components.ScreenWrapper
import com.example.heartstonetestapp.presentation.components.SortMenu
import com.example.heartstonetestapp.presentation.models.CardUI
import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import com.example.heartstonetestapp.presentation.util.CardsUIState
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews
import com.example.heartstonetestapp.presentation.util.WorkManagerUtils.begin
import kotlinx.coroutines.launch

@Composable
fun CardsListRoute(
  onCardClicked: () -> Unit,
  onSearchClicked: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CardsViewModel = hiltViewModel(),
  cardsSharedViewModel: CardsSharedViewModel,
) {

  val cardsState by viewModel.uiState.collectAsStateWithLifecycle()

  val context = LocalContext.current
  val workManager = WorkManager.getInstance(context)

  var hasNotificationPermission by remember {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      mutableStateOf(
        ContextCompat.checkSelfPermission(
          context,
          Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
      )
    } else mutableStateOf(true)
  }

  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission(),
    onResult = { isGranted ->
      hasNotificationPermission = isGranted
    }
  )

  val downloadRequest = OneTimeWorkRequestBuilder<CardUpdateWorker>()
    .setConstraints(
      Constraints.Builder()
        .setRequiredNetworkType(
          NetworkType.CONNECTED
        )
        .build()
    )
    .build()

  LaunchedEffect(Unit) {
    viewModel.getCardsByClass()
  }

  when (cardsState) {
    CardsUIState.Error -> {
      ErrorScreen(
        showTopAppBar = true,
        appBarTitle = R.string.app_bar_title_cards_list,
        errorMessage = R.string.general_error_message
      )
    }

    CardsUIState.Loading -> {
      LoadingScreen(
        showTopAppBar = true,
        appBarTitle = R.string.app_bar_title_cards_list,
      )
    }

    is CardsUIState.Success -> {
      val successState = cardsState as CardsUIState.Success
      CardsScreen(
        cards = successState.cards,
        onCardClicked = { index ->
          cardsSharedViewModel.setCardsList(successState.cards, index)
          onCardClicked()
        },
        onSearchClicked = onSearchClicked,
        onSortAscClicked = viewModel::sortCardsAscending,
        onSortDescClicked = viewModel::sortCardsDescending,
        onSortByClassClicked = viewModel::sortCardsByClass,
        onUpdateClicked = {
          workManager.begin(downloadRequest)
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
          }
        },
        modifier = modifier
      )
    }
  }
}

@Composable
private fun CardsScreen(
  cards: List<CardUI>,
  onCardClicked: (Int) -> Unit,
  onSearchClicked: () -> Unit,
  onSortAscClicked: () -> Unit,
  onSortDescClicked: () -> Unit,
  onSortByClassClicked: () -> Unit,
  onUpdateClicked: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val listState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()
  ScreenWrapper(
    modifier = modifier,
    showTopAppBar = true,
    appBarTitle = R.string.app_bar_title_cards_list,
    appBarActions = {

      Icon(
        imageVector = Icons.AutoMirrored.Default.Send,
        contentDescription = null,
        modifier = Modifier
          .padding(end = 16.dp)
          .clickable { onUpdateClicked() }
      )

      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null,
        modifier = Modifier
          .padding(end = 4.dp)
          .clickable { onSearchClicked() }
      )
      SortMenu(
        onSortAscClicked = {
          onSortAscClicked()
          coroutineScope.launch { listState.scrollToItem(0) }
        },
        onSortDescClicked = {
          onSortDescClicked()
          coroutineScope.launch { listState.scrollToItem(0) }
        },
        onSortByClassClicked = {
          onSortByClassClicked()
          coroutineScope.launch { listState.scrollToItem(0) }
        },
      )
    }
  ) {
    LazyColumn(
      state = listState,
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
    CardsScreen(
      cards = listOf(cardMock, cardMock),
      onSortAscClicked = { },
      onSortByClassClicked = { },
      onSortDescClicked = { },
      onCardClicked = { },
      onSearchClicked = { },
      onUpdateClicked = { }
    )
  }
}
