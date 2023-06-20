package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.DataHandler
import com.example.movieapp.NavigationRoute
import com.example.movieapp.ui.components.CreditsUIItem
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.TvShowUiItem
import com.example.movieapp.ui.components.common.TextWithIcon
import com.example.movieapp.ui.components.shimmerEffect
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.TvViewModel
import com.google.gson.Gson

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TVDetailsScreen(
    viewModel: TvViewModel = hiltViewModel(), movieId: Int?, navHostController: NavHostController
) {
    LaunchedEffect(key1 = "", block = {
        movieId?.let { movieId ->
            viewModel.getTvShowDetails(movieId)
            viewModel.getTvShowImages(movieId)
            viewModel.getSimilarTvShows(movieId)
            viewModel.getTvCredits(movieId)
        }
    })
    val movieDetailsResponse = viewModel.tvShowDetailsResponse.collectAsState()
    val creditResponse = viewModel.tvCreditsResponse.collectAsState()
    val similarMoviesResponse = viewModel.similarTvShowsResponse.collectAsState()
    val scrollState = rememberScrollState()
    println("scroll state" + scrollState.isScrollInProgress)
    val isCollapsed by remember {
        derivedStateOf { scrollState.value > 0 }
    }
    println("isCollapsed" + isCollapsed)
    movieDetailsResponse.value.let { result ->
        when (result) {
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
                val movieResponse = result.data
                println(">>>>>>>>>>>>>" + Gson().toJson(movieResponse))
                val movieImagesResponse = viewModel.tvShowImages.collectAsState()
                movieImagesResponse.value.let {
                    when (it) {
                        is DataHandler.Failure -> {
                        }

                        DataHandler.Loading -> {
                        }

                        is DataHandler.Success -> {
                            it.data.backdrops?.let { images ->
                                Scaffold(topBar = {
                                    DetailScreenAppBar(isCollapsed, images, movieResponse.name)
                                }) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(paddingValues = it)
                                            .verticalScroll(scrollState)
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Spacer(modifier = Modifier.size(10.dp))
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Column {
                                                    FlowRow(maxItemsInEachRow = 2) {
                                                        movieResponse.genres?.forEach { item ->
                                                            Surface(
                                                                modifier = Modifier.padding(5.dp),
                                                                shape = CircleShape,
                                                                color = Color.LightGray.copy(0.5f)
                                                            ) {
                                                                item?.name?.let { it1 ->
                                                                    Text(
                                                                        text = it1,
                                                                        fontWeight = FontWeight.SemiBold,
                                                                        color = Color.White,
                                                                        modifier = Modifier.padding(
                                                                            top = 5.dp,
                                                                            bottom = 5.dp,
                                                                            start = 10.dp,
                                                                            end = 10.dp
                                                                        ),
                                                                        textAlign = TextAlign.Start,
                                                                        fontSize = 16.sp
                                                                    )
                                                                }
                                                            }
                                                        }
                                                    }
                                                    Row(
                                                        modifier = Modifier.padding(top = 10.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        TextWithIcon(
                                                            Modifier.padding(top = 10.dp),
                                                            Icons.Filled.Star,
                                                            Color.Red,
                                                            movieResponse.voteAverage.toString()
                                                        )
                                                        Spacer(modifier = Modifier.size(10.dp))
                                                        /*TextWithIcon(
                                                            Modifier.padding(top = 10.dp),
                                                            Icons.Filled.DateRange,
                                                            Color.Red,
                                                            movieResponse.releaseDate.toString()
                                                        )*/
                                                    }
                                                }

                                                AsyncImage(
                                                    modifier = Modifier
                                                        .width(100.dp)
                                                        .height(100.dp)
                                                        .padding(start = 5.dp)
                                                        .clip(RoundedCornerShape(5.dp))
                                                        .shimmerEffect(),
                                                    contentScale = ContentScale.FillBounds,
                                                    model = movieResponse.getDefaultImagePath(),
                                                    contentDescription = ""
                                                )
                                            }

                                        }
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(
                                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                            text = "Overview",
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.size(10.dp))
                                        movieResponse.overview?.let { overview ->
                                            Text(
                                                modifier = Modifier.padding(
                                                    start = 16.dp, end = 16.dp
                                                ),
                                                text = overview,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.White
                                            )
                                        }
                                        Spacer(modifier = Modifier.size(10.dp))
                                        CreditsUIItem(creditResponse.value,
                                            "Cast",
                                            itemClick = { movieId ->
                                                navHostController.apply {
                                                    currentBackStackEntry?.savedStateHandle?.set(
                                                        "movie_id", movieId
                                                    )
                                                    navigate(NavigationRoute.PERSON_DETAILS.route)
                                                }
                                            }
                                        ) {
                                            navHostController.apply {
                                                currentBackStackEntry?.savedStateHandle?.set(
                                                    "credit_response", it
                                                )
                                                navigate(NavigationRoute.CREDITS_VIEW_ALL.route)
                                            }
                                        }
                                        Spacer(modifier = Modifier.size(10.dp))
                                        TvShowUiItem(similarMoviesResponse,
                                            "Similar Movies",
                                            itemClick = { movieId ->
                                                navHostController.apply {
                                                    currentBackStackEntry?.savedStateHandle?.set(
                                                        "movie_id", movieId
                                                    )
                                                    navigate(NavigationRoute.MOVIE_DETAILS.route)
                                                }
                                            },
                                            viewAllItemClick = {
                                                navHostController.apply {
                                                    currentBackStackEntry?.savedStateHandle?.apply {
                                                        set("title", "Upcoming Movies")
                                                        set(
                                                            "url", Constants.URL_UPCOMING_MOVIES
                                                        )
                                                    }
                                                    navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                                                }
                                            })
                                        Spacer(modifier = Modifier.size(10.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}