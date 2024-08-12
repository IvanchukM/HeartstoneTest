package com.example.heartstonetestapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenWrapper(
  modifier: Modifier = Modifier,
  contentModifier: Modifier = Modifier,
  showTopAppBar: Boolean = false,
  @StringRes appBarTitle: Int? = null,
  showNavigateUp: Boolean = false,
  onNavigateUp: () -> Unit = {},
  appBarActions: @Composable RowScope.() -> Unit = {},
  topAppBar: @Composable () -> Unit = {
    TopAppBar(
      appBarTitle = appBarTitle,
      showNavigateUp = showNavigateUp,
      onNavigateUp = onNavigateUp,
      appBarActions = appBarActions
    )
  },
  verticalArrangement: Arrangement.Vertical = Arrangement.Top,
  horizontalAlignment: Alignment.Horizontal = Alignment.Start,
  content: @Composable ColumnScope.() -> Unit
) {
  Column(modifier = modifier.fillMaxSize()) {
    if (showTopAppBar) {
      topAppBar()
    }
    Column(
      modifier = contentModifier
        .fillMaxSize()
        .weight(1f),
      verticalArrangement = verticalArrangement,
      horizontalAlignment = horizontalAlignment
    ) {
      content()
    }
  }
}
