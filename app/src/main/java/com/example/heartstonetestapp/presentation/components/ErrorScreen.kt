package com.example.heartstonetestapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun ErrorScreen(
  modifier: Modifier = Modifier,
  showTopAppBar: Boolean = false,
  @StringRes appBarTitle: Int? = null,
  showNavigateUp: Boolean = false,
  @StringRes errorMessage: Int,
  onNavigateUp: () -> Unit = {},
) {
  ScreenWrapper(
    modifier = modifier,
    showTopAppBar = showTopAppBar,
    appBarTitle = appBarTitle,
    showNavigateUp = showNavigateUp,
    onNavigateUp = onNavigateUp,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_error),
      contentDescription = null,
      modifier = Modifier.size(size = 80.dp)
    )
    Text(text = stringResource(id = errorMessage))
  }
}

/** Previews **/

@Composable
@ThemePreviews
private fun ErrorScreenPreview() {
  ScreenThemeWrapper {
    ErrorScreen(
      showTopAppBar = true,
      showNavigateUp = true,
      appBarTitle = R.string.preview_title,
      errorMessage = R.string.preview_error_msg
    )
  }
}
