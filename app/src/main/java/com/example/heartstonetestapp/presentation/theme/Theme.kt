package com.example.heartstonetestapp.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
  primary = Blue400,
  primaryContainer = Blue600,
  onPrimary = Color.White,
  background = Gray900,
  onBackground = Color.White,
  surface = Gray900,
  onSurface = Color.White,
  error = Red500,
  onError = Color.White,
  secondary = Blue400,
  secondaryContainer = Blue600,
  onSecondary = Color.White
)

private val LightColorScheme = lightColorScheme(
  primary = Blue400,
  primaryContainer = Blue600,
  onPrimary = Color.White,
  background = Gray50,
  onBackground = Color.Black,
  surface = Gray50,
  onSurface = Color.Black,
  error = Red400,
  onError = Color.White,
  secondary = Blue400,
  secondaryContainer = Blue600,
  onSecondary = Color.White
)

@Composable
fun HeartstoneTestAppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}