package com.example.heartstonetestapp.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.heartstonetestapp.presentation.navigation.utils.TopLevelDestination

@Composable
fun BottomNavigationBar(navController: NavHostController) {

  val navItems = listOf(TopLevelDestination.CARDS, TopLevelDestination.FAVOURITE)

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  NavigationBar {
    navItems.forEach { item ->
      val title = stringResource(item.titleResId)
      NavigationBarItem(
        alwaysShowLabel = true,
        icon = { Icon(item.iconResId, contentDescription = title) },
        label = { Text(title) },
        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
        onClick = {
          navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
          }
        }
      )
    }
  }
}
