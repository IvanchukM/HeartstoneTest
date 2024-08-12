package com.example.heartstonetestapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.heartstonetestapp.presentation.navigation.utils.CardsNavigationRoutes
import com.example.heartstonetestapp.presentation.navigation.utils.NavigationUtil
import com.example.heartstonetestapp.presentation.navigation.utils.getViewModel
import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import com.example.heartstonetestapp.presentation.screens.details.CardDetailsRoute
import com.example.heartstonetestapp.presentation.screens.list.CardsListRoute
import com.example.heartstonetestapp.presentation.screens.search.SearchRoute

fun NavGraphBuilder.cardsNavigation(navController: NavController) {
  navigation(
    route = CardsNavigationRoutes.BASE_ROUTE,
    startDestination = CardsNavigationRoutes.CARDS_LIST_ROUTE
  ) {
    composable(
      route = CardsNavigationRoutes.CARDS_LIST_ROUTE
    ) { backStackEntry ->
      val cardsSharedViewModel = getViewModel<CardsSharedViewModel>(
        navController = navController,
        backStackEntry = backStackEntry,
        route = CardsNavigationRoutes.BASE_ROUTE
      )

      CardsListRoute(
        cardsSharedViewModel = cardsSharedViewModel,
        onCardClicked = {
          val cardDetailsRoute = NavigationUtil.getFormattedRoute(
            CardsNavigationRoutes.CARDS_DETAILS_ROUTE
          )
          navController.navigate(cardDetailsRoute)
        },
        onSearchClicked = {
          val cardSearchRoute = NavigationUtil.getFormattedRoute(
            CardsNavigationRoutes.CARDS_SEARCH_ROUTE
          )
          navController.navigate(cardSearchRoute)
        }
      )
    }

    composable(
      route = CardsNavigationRoutes.CARDS_DETAILS_ROUTE
    ) { backStackEntry ->
      val cardsSharedViewModel = getViewModel<CardsSharedViewModel>(
        navController = navController,
        backStackEntry = backStackEntry,
        route = CardsNavigationRoutes.BASE_ROUTE
      )

      CardDetailsRoute(
        onNavigateUp = navController::popBackStack,
        cardsSharedViewModel = cardsSharedViewModel
      )
    }
    composable(
      route = CardsNavigationRoutes.CARDS_SEARCH_ROUTE
    ) {
      SearchRoute(onNavigateUp = navController::popBackStack)
    }
  }
}
