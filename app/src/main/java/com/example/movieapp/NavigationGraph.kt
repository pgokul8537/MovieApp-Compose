package com.example.movieapp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.ui.screens.CreditsViewAllScreen
import com.example.movieapp.ui.screens.ExploreScreen
import com.example.movieapp.ui.screens.MovieDetailsScreen
import com.example.movieapp.ui.screens.MovieViewAllScreen
import com.example.movieapp.ui.screens.MoviesScreen
import com.example.movieapp.ui.screens.PersonDetailsScreen
import com.example.movieapp.ui.screens.TvShowsScreen
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
        composable(NavigationRoute.MOVIE_DETAILS.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }, popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }) {
            val movieId =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movie_id")
            MovieDetailsScreen(movieId = movieId, navHostController = navController)
        }
        composable(NavigationRoute.MOVIES_VIEW_ALL.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }, popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val title = get<String>("title")
                val url = get<String>("url")
                title?.let { title ->
                    url?.let { url ->
                        MovieViewAllScreen(
                            title = title, url = url,
                            navHostController = navController
                        )
                    }
                }
            }
        }
        composable(NavigationRoute.CREDITS_VIEW_ALL.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }, popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }) {
            val viewModel = hiltViewModel<MovieViewModel>()
            CreditsViewAllScreen(viewModel.creditResponse.collectAsState(),navHostController = navController)
        }
        composable(NavigationRoute.PERSON_DETAILS.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
        }, popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }, popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
        }) {
            val movieId =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movie_id")
            PersonDetailsScreen(personId = movieId, navHostController = navController)
        }
    }

}