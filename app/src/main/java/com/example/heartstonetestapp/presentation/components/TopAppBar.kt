package com.example.heartstonetestapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
  @StringRes appBarTitle: Int? = null,
  showNavigateUp: Boolean = false,
  onNavigateUp: () -> Unit = {},
  appBarActions: @Composable RowScope.() -> Unit = {},
  backgroundColor: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = contentColorFor(backgroundColor)
) {
  TopAppBar(
    title = {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (appBarTitle != null) stringResource(appBarTitle) else "")
      }
    },
    navigationIcon = {
      if (showNavigateUp) {
        NavigateUpButton(onNavigateUp = onNavigateUp)
      }
    },
    actions = appBarActions,
    colors = TopAppBarColors(
      containerColor = backgroundColor,
      scrolledContainerColor = backgroundColor,
      navigationIconContentColor = contentColor,
      titleContentColor = contentColor,
      actionIconContentColor = contentColor
    )
  )
}

/** Previews **/

@Composable
@ThemePreviews
private fun TopAppBarPreview() {
  ScreenThemeWrapper {
    TopAppBar(
      showNavigateUp = true,
      appBarTitle = R.string.preview_title,
    )
  }
}