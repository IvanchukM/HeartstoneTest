package com.example.heartstonetestapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.util.ScreenThemeWrapper
import com.example.heartstonetestapp.presentation.util.ThemePreviews

@Composable
fun SortMenu(
  onSortAscClicked: () -> Unit,
  onSortDescClicked: () -> Unit,
  onSortByClassClicked: () -> Unit,
) {
  var isExpanded by remember { mutableStateOf(false) }

  Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Sort",
    modifier = Modifier.clickable {
      isExpanded = true
    })

  DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
    DropdownMenuItem(
      text = { Text(text = stringResource(id = R.string.sort_by_asc_title)) },
      onClick = {
        onSortAscClicked()
        isExpanded = false
      })

    DropdownMenuItem(
      text = { Text(text = stringResource(id = R.string.sort_by_desc_title)) },
      onClick = {
        onSortDescClicked()
        isExpanded = false
      })

    DropdownMenuItem(
      text = { Text(text = stringResource(id = R.string.sort_by_class_title)) },
      onClick = {
        onSortByClassClicked()
        isExpanded = false
      })
  }
}

/** Previews **/

@Composable
@ThemePreviews
private fun SortMenuPreview() {
  ScreenThemeWrapper {
    SortMenu(
      onSortByClassClicked = { },
      onSortDescClicked = { },
      onSortAscClicked = { },
    )
  }
}
