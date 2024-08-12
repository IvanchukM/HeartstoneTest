package com.example.heartstonetestapp.presentation.navigation.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.heartstonetestapp.R

object NavigationUtil {
  fun getFormattedRoute(route: String, vararg formatArguments: FormatArgument): String {
    var formattedRoute = route
    for (formatArgument in formatArguments) {
      val replaceKey = "{${formatArgument.name}}"
      formattedRoute = formattedRoute.replace(oldValue = replaceKey, newValue = formatArgument.value.toString())
    }
    return formattedRoute
  }
}

data class FormatArgument(val name: String, val value: Any?)

@Composable
inline fun <reified VM : ViewModel> getViewModel(
  navController: NavController,
  backStackEntry: NavBackStackEntry,
  route: String
): VM {
  val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(route) }
  return hiltViewModel(parentEntry)
}


enum class TopLevelDestination(
  val route: String,
  @StringRes val titleResId: Int,
  val iconResId: ImageVector,
) {
  CARDS(
    route = CardsNavigationRoutes.BASE_ROUTE,
    titleResId = R.string.bottom_navigation_cards,
    iconResId = Icons.AutoMirrored.Outlined.List
  ),
  FAVOURITE(
    route = FavouriteCardsNavigationRoutes.BASE_ROUT,
    titleResId = R.string.bottom_navigation_favourite,
    iconResId = Icons.Outlined.FavoriteBorder
  ),
}