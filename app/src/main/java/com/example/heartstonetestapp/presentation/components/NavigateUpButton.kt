package com.example.heartstonetestapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun NavigateUpButton(
  onNavigateUp: () -> Unit,
  modifier: Modifier = Modifier
) {
  IconButton(
    onClick = onNavigateUp,
    modifier = modifier
  ) {
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowBack,
      contentDescription = null,
    )
  }
}


/** Previews **/

@Composable
@ThemePreviews
private fun LoadingScreenPreview() {
  ScreenThemeWrapper {
    NavigateUpButton(
      onNavigateUp = { }
    )
  }
}