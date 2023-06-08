package com.example.movieapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Movies.route
    )
    {
        composable(NavigationRoute.MOVIES.route) {
            val viewModel = hiltViewModel<MovieViewModel>()
            MoviesScreen(viewModel, navController)
        }
        composable(NavigationRoute.TV_SHOWS.route) {
            TvShowsScreen()
        }
        composable(NavigationRoute.EXPLORE.route) {
            ExploreScreen()
        }
        composable(NavigationRoute.MOVIE_DETAILS.route) {
            val movieId =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movie_id")
            MovieDetailsScreen(movieId, navController)
        }
        composable(NavigationRoute.MOVIES_VIEW_ALL.route) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("title")
            result?.let { title -> MovieViewAllScreen(title, navController) }
        }
    }

}