package com.example.heartstonetestapp.presentation.main_screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.presentation.components.ErrorScreen
import com.example.heartstonetestapp.presentation.components.LoadingScreen
import com.example.heartstonetestapp.presentation.theme.HeartstoneTestAppTheme
import com.example.heartstonetestapp.presentation.util.LoadingState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel: MainActivityViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      viewModel.loadCards()
    }
    enableEdgeToEdge()
    setContent {
      val initialLoadingState by viewModel.initialLoadingState.collectAsStateWithLifecycle()
      when (initialLoadingState) {
        LoadingState.Error -> {
          HeartstoneTestAppTheme {
            ErrorScreen(
              errorMessage = R.string.general_error_message
            )
          }
        }

        LoadingState.Loading -> {
          HeartstoneTestAppTheme {
            LoadingScreen(
              loadingMessage = R.string.initial_loading_message
            )
          }
        }

        is LoadingState.Success -> {
          HeartstoneTestAppTheme {
            MainScreen()
          }
        }
      }
    }
  }
}


