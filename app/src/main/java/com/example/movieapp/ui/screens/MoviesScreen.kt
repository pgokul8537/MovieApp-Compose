package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.NavigationRoute
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(viewModel: MovieViewModel, navHostController: NavHostController) {
    LaunchedEffect(key1 = "", block = {
        viewModel.getPopularMovies(1)
        viewModel.getNowPlayingMovies(1)
        viewModel.getTopRatedMovies(1)
        viewModel.getUpcomingMovies(1)
    })
    val popularMoviesResponse = viewModel.popularMoviesResponse.value
    val topRatedMoviesResponse = viewModel.topRatedMoviesResponse.value
    val upcomingMoviesResponse = viewModel.upcomingMoviesResponse.value
    val nowPlayingMoviesResponse = viewModel.nowPlayingMoviesResponse.value

    /*Scaffold(
        contentColor = Color.Black,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Black),
                title = {
                    Text(
                        text = "Movie",
                        color = Color.Red,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->*/
    Surface {
        val contentPadding = WindowInsets.navigationBars.add(
            WindowInsets(
                top = 16.dp,
                bottom = 16.dp
            )
        ).asPaddingValues()
        LazyColumn(contentPadding = contentPadding, content = {
            item {
                MoviesUiItem(
                    popularMoviesResponse,
                    "Popular",
                    itemClick = { movieId ->
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                            navigate(NavigationRoute.MOVIE_DETAILS.route)
                        }
                    },
                    viewAllItemClick = {
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.apply {
                                set("title", "Popular Movies")
                                set("url", Constants.urlPopularMovies)
                            }
                            navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                        }
                    })
            }
            item {
                MoviesUiItem(topRatedMoviesResponse, "Top Rated",
                    itemClick = { movieId ->
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                            navigate(NavigationRoute.MOVIE_DETAILS.route)
                        }
                    },
                    viewAllItemClick = {
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.apply {
                                set("title", "Top Rated Movies")
                                set("url", Constants.urlTopRatedMovies)
                            }
                            navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                        }
                    })
            }
            item {
                MoviesUiItem(upcomingMoviesResponse, "Upcoming",
                    itemClick = { movieId ->
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                            navigate(NavigationRoute.MOVIE_DETAILS.route)
                        }
                    },
                    viewAllItemClick = {
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.apply {
                                set("title", "Upcoming Movies")
                                set("url", Constants.urlUpcomingMovies)
                            }
                            navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                        }
                    })
            }
            item {
                MoviesUiItem(nowPlayingMoviesResponse, "Now Playing",
                    itemClick = { movieId ->
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                            navigate(NavigationRoute.MOVIE_DETAILS.route)
                        }
                    },
                    viewAllItemClick = {
                        navHostController.apply {
                            currentBackStackEntry?.savedStateHandle?.apply {
                                set("title", "Now Playing Movies")
                                set("url", Constants.urlNowPlayingMovies)
                            }
                            navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                        }
                    })
            }
        })
    }
//    }
}

@Composable
fun Progress() {
    CircularProgressIndicator()
}