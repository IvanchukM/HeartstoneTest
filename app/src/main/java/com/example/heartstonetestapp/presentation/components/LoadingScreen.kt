package com.example.heartstonetestapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun LoadingScreen(
  modifier: Modifier = Modifier,
  onNavigateUp: () -> Unit = {},
  @StringRes appBarTitle: Int? = null,
  @StringRes loadingMessage: Int? = null,
  showNavigateUp: Boolean = false,
  showTopAppBar: Boolean = false,
) {
  ScreenWrapper(
    modifier = modifier,
    showTopAppBar = showTopAppBar,
    showNavigateUp = showNavigateUp,
    onNavigateUp = onNavigateUp,
    appBarTitle = appBarTitle,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    CircularProgressIndicator(
      modifier = Modifier.width(64.dp),
      color = MaterialTheme.colorScheme.secondary,
      trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
    if (loadingMessage != null) {
      Text(
        text = stringResource(id = loadingMessage),
        modifier = Modifier
          .padding(vertical = 32.dp)
      )
    }
  }
}

/** Previews **/

@Composable
@ThemePreviews
private fun LoadingScreenPreview() {
  ScreenThemeWrapper {
    LoadingScreen(
      showTopAppBar = true,
      showNavigateUp = true,
      appBarTitle = R.string.preview_title,
      loadingMessage = R.string.preview_loading_msg
    )
  }
}
