package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.DataHandler
import com.example.movieapp.NavigationRoute
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.TvShowHorizontalPagerWithImage
import com.example.movieapp.ui.components.TvShowUiItem
import com.example.movieapp.viewmodel.TvViewModel

@Composable
fun TvShowsScreen(viewModel: TvViewModel = hiltViewModel(), navHostController: NavHostController) {
    LaunchedEffect(key1 = "") {
        viewModel.getTrendingMovies()
        viewModel.getPopularTvShows()
        viewModel.getAiringTodayTvShows()
        viewModel.getOnTheAirTvShows()
        viewModel.getTopRatedTvShows()
    }
    val scrollState = rememberScrollState()
    val trendingMoviesResponse = viewModel.trendingMoviesResponse.collectAsState()
    val popularTvShowsResponse = viewModel.popularTvShowsResponse.collectAsState()
    val airingTodayTvShowsResponse = viewModel.airingTodayTvShowsResponse.collectAsState()
    val onTheAirTvShowsResponse = viewModel.onTheAirTvShowsResponse.collectAsState()
    val topRatedTvShows = viewModel.topRatedTvShows.collectAsState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            trendingMoviesResponse.value.let {
                when (it) {
                    is DataHandler.Failure -> {}
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
                        it.data.results?.let { list ->
                            TvShowHorizontalPagerWithImage(list, onItemClick = {})
                        }
                    }
                }
            }
            TvShowUiItem(
                onTheAirTvShowsResponse, "On The Air",
                itemClick = { movieId ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                        navigate(NavigationRoute.TV_SHOWS_DETAILS.route)
                    }
                },
                viewAllItemClick = {}, toShowViewAll = false
            )

            TvShowUiItem(
                airingTodayTvShowsResponse, "Airing Today",
                itemClick = { movieId ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                        navigate(NavigationRoute.TV_SHOWS_DETAILS.route)
                    }
                },
                viewAllItemClick = {}, toShowViewAll = false
            )
            TvShowUiItem(
                popularTvShowsResponse, "Popular",
                itemClick = { movieId ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                        navigate(NavigationRoute.TV_SHOWS_DETAILS.route)
                    }
                },
                viewAllItemClick = {}, toShowViewAll = false
            )
            TvShowUiItem(
                topRatedTvShows, "Top Rated",
                itemClick = { movieId ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("movie_id", movieId)
                        navigate(NavigationRoute.TV_SHOWS_DETAILS.route)
                    }
                },
                viewAllItemClick = {}, toShowViewAll = false
            )
        }
    }
}