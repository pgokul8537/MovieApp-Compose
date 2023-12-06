package com.example.movieapp.ui.screens

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.DataHandler
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.TvShowHorizontalPagerWithImage
import com.example.movieapp.ui.components.TvShowUiItem
import com.example.movieapp.viewmodel.TvViewModel

@Composable
fun TvShowsScreen(viewModel: TvViewModel = hiltViewModel(), onTVClick: (tvShowId: Int) -> Unit) {
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
                            TvShowHorizontalPagerWithImage(list, onItemClick = { tvShowId ->
                                tvShowId?.let {
                                    onTVClick(it)
                                }
                            })
                        }
                    }
                }
            }
            TvShowUiItem(onTheAirTvShowsResponse, "On The Air", itemClick = { tvShowId ->
                tvShowId?.let {
                    onTVClick(it)
                }
            }, viewAllItemClick = {}, toShowViewAll = false
            )

            TvShowUiItem(airingTodayTvShowsResponse, "Airing Today", itemClick = { tvShowId ->
                tvShowId?.let {
                    onTVClick(it)
                }
            }, viewAllItemClick = {}, toShowViewAll = false
            )
            TvShowUiItem(popularTvShowsResponse, "Popular", itemClick = { tvShowId ->
                tvShowId?.let {
                    onTVClick(it)
                }
            }, viewAllItemClick = {}, toShowViewAll = false
            )
            TvShowUiItem(topRatedTvShows, "Top Rated", itemClick = { tvShowId ->
                tvShowId?.let {
                    onTVClick(it)
                }
            }, viewAllItemClick = {}, toShowViewAll = false
            )
        }
    }
    val context = LocalContext.current
    BackHandler {
        context.findActivity()?.finish()
    }
}

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}