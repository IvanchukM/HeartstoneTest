package com.example.heartstonetestapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.heartstonetestapp.presentation.navigation.utils.FavouriteCardsNavigationRoutes
import com.example.heartstonetestapp.presentation.navigation.utils.NavigationUtil
import com.example.heartstonetestapp.presentation.navigation.utils.getViewModel
import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import com.example.heartstonetestapp.presentation.screens.details.CardDetailsRoute
import com.example.heartstonetestapp.presentation.screens.favourite.FavouriteCardsRoute

fun NavGraphBuilder.favouriteCardsNavigation(navController: NavController) {
  navigation(
    route = FavouriteCardsNavigationRoutes.BASE_ROUT,
    startDestination = FavouriteCardsNavigationRoutes.CARDS_FAVOURITE_ROUTE
  ) {
    composable(
      route = FavouriteCardsNavigationRoutes.CARDS_FAVOURITE_ROUTE
    ) { backStackEntry ->

      val cardsSharedViewModel = getViewModel<CardsSharedViewModel>(
        navController = navController,
        backStackEntry = backStackEntry,
        route = FavouriteCardsNavigationRoutes.BASE_ROUT
      )

      FavouriteCardsRoute(
        cardsSharedViewModel = cardsSharedViewModel,
        onCardClicked = {
          val cardDetailsRoute = NavigationUtil.getFormattedRoute(
            FavouriteCardsNavigationRoutes.FAVOURITE_CARDS_DETAILS_ROUTE,
          )
          navController.navigate(cardDetailsRoute)
        }
      )
    }

    composable(
      route = FavouriteCardsNavigationRoutes.FAVOURITE_CARDS_DETAILS_ROUTE
    ) { backStackEntry ->

      val cardsSharedViewModel = getViewModel<CardsSharedViewModel>(
        navController = navController,
        backStackEntry = backStackEntry,
        route = FavouriteCardsNavigationRoutes.BASE_ROUT
      )

      CardDetailsRoute(
        onNavigateUp = navController::popBackStack,
        cardsSharedViewModel = cardsSharedViewModel
      )
    }
  }
}
