package com.example.heartstonetestapp.presentation.util

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.heartstonetestapp.presentation.theme.HeartstoneTestAppTheme

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Theme")
annotation class ThemePreviews


@Composable
fun ScreenThemeWrapper(content: @Composable () -> Unit) {
  HeartstoneTestAppTheme {
    Surface {
      content()
    }
  }
}
