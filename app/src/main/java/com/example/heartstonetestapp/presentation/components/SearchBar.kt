package com.example.heartstonetestapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun SearchBar(
  searchTerm: String,
  onSearchTermChanged: (String) -> Unit,
  modifier: Modifier = Modifier,
  leadingIcon: @Composable (() -> Unit)? = null
) {
  Row(modifier = modifier.fillMaxWidth()) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = searchTerm,
      onValueChange = onSearchTermChanged,
      singleLine = true,
      label = { Text(text = stringResource(R.string.search_bar)) },
      leadingIcon = {
        if (leadingIcon != null) {
          leadingIcon()
        } else {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
          )
        }
      },
    )
  }
}

/** Preview **/

@Composable
@ThemePreviews
private fun SearchBarPreview() {
  ScreenThemeWrapper {
    SearchBar(
      searchTerm = "",
      onSearchTermChanged = { }
    )
  }
}

@Composable
@ThemePreviews
private fun SearchBarTextPreview() {
  ScreenThemeWrapper {
    SearchBar(
      searchTerm = "Test Term",
      onSearchTermChanged = { }
    )
  }
}