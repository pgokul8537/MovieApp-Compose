package com.example.movieapp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.ui.screens.CreditsViewAllScreen
import com.example.movieapp.ui.screens.ExploreScreen
import com.example.movieapp.ui.screens.MovieDetailsScreen
import com.example.movieapp.ui.screens.MovieViewAllScreen
import com.example.movieapp.ui.screens.MoviesScreen
import com.example.movieapp.ui.screens.PersonDetailsScreen
import com.example.movieapp.ui.screens.SearchScreen
import com.example.movieapp.ui.screens.TVDetailsScreen
import com.example.movieapp.ui.screens.TvShowsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = BottomNavItem.Movies.route
    ) {
        val goBack: () -> Unit = {
            navController.popBackStack()
        }
        val goToMovieDetails: (movieId: Int) -> Unit = { movieId ->
            navController.apply {
                currentBackStackEntry?.savedStateHandle?.set(
                    "movie_id", movieId
                )
                navigate(NavigationRoute.MOVIE_DETAILS.route)
            }
        }
        val goToTvDetails: (movieId: Int) -> Unit = { movieId ->
            navController.apply {
                currentBackStackEntry?.savedStateHandle?.set(
                    "movie_id", movieId
                )
                navigate(NavigationRoute.TV_SHOWS_DETAILS.route)
            }
        }

        val goToAllMovies: (title: String, url: String) -> Unit = { title, url ->
            navController.apply {
                currentBackStackEntry?.savedStateHandle?.apply {
                    set("title", title)
                    set("url", url)
                }
                navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
            }
        }

        val goToSearch: (title: String, url: String) -> Unit = { title, url ->
            navController.apply {
                currentBackStackEntry?.savedStateHandle?.apply {
                    set("search_type", title)
                    set("url", url)
                }
                navigate(NavigationRoute.SEARCH.route)
            }
        }

        val goToPersonDetails: (personId: Int) -> Unit = { personId ->
            navController.apply {
                currentBackStackEntry?.savedStateHandle?.set(
                    "movie_id", personId
                )
                navigate(NavigationRoute.PERSON_DETAILS.route)
            }
        }
        val goToAllCredits: (creditsResponse: CreditResponse) -> Unit = {
            navController.apply {
                currentBackStackEntry?.savedStateHandle?.set(
                    "credit_response", it
                )
                navigate(NavigationRoute.CREDITS_VIEW_ALL.route)
            }
        }

        slideComposable(NavigationRoute.MOVIES.route) {
            MoviesScreen(onMovieClick = goToMovieDetails, onViewAllMoviesClick = goToAllMovies)
        }
        slideComposable(NavigationRoute.TV_SHOWS.route) {
            TvShowsScreen(onTVClick = goToTvDetails)
        }
        slideComposable(NavigationRoute.EXPLORE.route) {
            ExploreScreen(onSearchClick = goToSearch)
        }
        slideComposable(NavigationRoute.MOVIE_DETAILS.route) {
            val movieId =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movie_id")
            MovieDetailsScreen(
                movieId = movieId,
                onBackClick = goBack,
                onMovieClick = goToMovieDetails,
                onViewAllMoviesClick = goToAllMovies,
                onPersonClick = goToPersonDetails,
                onViewAllCreditsClick = goToAllCredits
            )
        }
        slideComposable(NavigationRoute.TV_SHOWS_DETAILS.route) {
            val movieId =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movie_id")
            TVDetailsScreen(
                movieId = movieId,
                onViewAllMoviesClick = goToAllMovies,
                onTVClick = goToTvDetails,
                onBackClick = goBack, onPersonClick = goToPersonDetails,
                onViewAllCreditsClick = goToAllCredits
            )
        }
        slideComposable(NavigationRoute.MOVIES_VIEW_ALL.route) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val title = get<String>("title")
                val url = get<String>("url")
                title?.let { title ->
                    url?.let { url ->
                        MovieViewAllScreen(
                            title = title, url = url, onMovieClick = goToMovieDetails,
                            onSearchMoviesClick = goToSearch, onBackClick = goBack
                        )
                    }
                }
            }
        }
        slideComposable(NavigationRoute.CREDITS_VIEW_ALL.route) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val creditResponse = get<CreditResponse>("credit_response")
                creditResponse?.let { response ->
                    CreditsViewAllScreen(
                        response,
                        onSearchClick = goToSearch,
                        onBackClick = goBack,
                        onPersonClick = goToPersonDetails
                    )
                }
            }


        }
        slideComposable(NavigationRoute.PERSON_DETAILS.route) {
            val movieId =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movie_id")
            PersonDetailsScreen(
                personId = movieId, onMovieClick = goToMovieDetails,
                onViewAllMoviesClick = goToAllMovies, onBackClick = goBack
            )
        }
        slideComposable(NavigationRoute.SEARCH.route) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val searchType = get<String>("search_type")
                val url = get<String>("url")
                searchType?.let { type ->
                    url?.let {
                        SearchScreen(
                            searchType = type, url = it, onBackClick = goBack
                        )
                    }
                }
            }
        }
    }

}

fun NavGraphBuilder.slideComposable(route: String, content: @Composable () -> Unit) {

    val isRouteParentDestination: (String?) -> Boolean = {
        when (it) {
            NavigationRoute.TV_SHOWS.route, NavigationRoute.EXPLORE.route, NavigationRoute.MOVIES.route -> true
            else -> false
        }
    }
    val animationSpec = tween<IntOffset>(700, easing = FastOutSlowInEasing)
    composable(route = route, content = {
        content()
    }, enterTransition = {
        if (isRouteParentDestination(targetState.destination.route)) {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = animationSpec
            )
        } else {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = animationSpec
            )
        }
    }, exitTransition = {
        if (isRouteParentDestination(targetState.destination.route)) {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = animationSpec
            )
        } else {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = animationSpec
            )
        }
    }, popEnterTransition = {
        if (isRouteParentDestination(initialState.destination.route)) {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = animationSpec
            )
        } else {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = animationSpec
            )
        }
    }, popExitTransition = {
        if (isRouteParentDestination(initialState.destination.route)) {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = animationSpec
            )
        } else {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = animationSpec
            )
        }
    })

}