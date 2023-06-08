package com.example.movieapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController) {
    val bottomNavItems = listOf(BottomNavItem.Movies, BottomNavItem.TvShows, BottomNavItem.Explore)
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    when (navBackStackEntry?.destination?.route) {
        NavigationRoute.MOVIES_VIEW_ALL.route -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
        }

        NavigationRoute.MOVIE_DETAILS.route -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
        }

        else -> {
            bottomBarState.value = true
        }
    }
    AnimatedVisibility(visible = bottomBarState.value) {
        NavigationBar(containerColor = Color.Red) {
            bottomNavItems.forEach { item ->
//            val selected = item.route == currentRoute
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = { Text(text = item.title, fontWeight = FontWeight.SemiBold) },
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    })
            }
        }

    }

}