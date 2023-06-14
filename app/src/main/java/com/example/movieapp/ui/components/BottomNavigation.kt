package com.example.movieapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieapp.BottomNavItem
import com.example.movieapp.NavigationRoute

@Composable
fun BottomNavigation(navController: NavController) {
    val bottomNavItems = listOf(BottomNavItem.Movies, BottomNavItem.TvShows, BottomNavItem.Explore)
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    when (navBackStackEntry?.destination?.route) {
        NavigationRoute.MOVIES_VIEW_ALL.route, NavigationRoute.SEARCH.route, NavigationRoute.MOVIE_DETAILS.route, NavigationRoute.CREDITS_VIEW_ALL.route, NavigationRoute.PERSON_DETAILS.route -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
        }

        else -> {
            bottomBarState.value = true
        }
    }
    AnimatedVisibility(visible = bottomBarState.value) {
        NavigationBar(containerColor = Color.Black, tonalElevation = 16.dp) {
            bottomNavItems.forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(selected = selected,
                    colors = NavigationBarItemDefaults.colors(Color.Red),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                            color = if (selected) Color.Red else Color.White
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = if (selected) Color.Red else Color.White
                        )
                    })
            }
        }

    }

}