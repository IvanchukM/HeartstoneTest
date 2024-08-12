package com.example.heartstonetestapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heartstonetestapp.presentation.navigation.utils.CardsNavigationRoutes

@Composable
fun NavigationRootGraph(
  navController: NavHostController = rememberNavController()
) {
  NavHost(
    navController = navController,
    startDestination = CardsNavigationRoutes.BASE_ROUTE
  ) {
    cardsNavigation(navController = navController)
    favouriteCardsNavigation(navController = navController)
  }
}

