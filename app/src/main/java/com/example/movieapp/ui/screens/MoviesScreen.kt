package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.DataHandler
import com.example.movieapp.NavigationRoute
import com.example.movieapp.ui.components.HorizontalPagerWithImage
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Black)
    systemUiController.setNavigationBarColor(Color.Black)
    systemUiController.setSystemBarsColor(Color.Black)
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
                when (popularMoviesResponse) {
                    is DataHandler.Failure -> {
                    }

                    DataHandler.Loading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            MovieProgress()
                        }
                    }

                    is DataHandler.Success -> {
                        val movieList = popularMoviesResponse.data.results

                        movieList?.let {
                            HorizontalPagerWithImage(movieItem = it)
                        }
                    }
                }
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