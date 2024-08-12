package com.example.heartstonetestapp.presentation.main_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heartstonetestapp.presentation.navigation.BottomNavigationBar
import com.example.heartstonetestapp.presentation.navigation.NavigationRootGraph

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
  Scaffold(bottomBar = {
    BottomAppBar { BottomNavigationBar(navController = navController) }
  }) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .consumeWindowInsets(padding)
    ) {
      NavigationRootGraph(
        navController = navController
      )
    }
  }
}
