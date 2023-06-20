package com.example.movieapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
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
import coil.compose.AsyncImage
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.ImageItem
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.ui.components.CreditsUIItem
import com.example.movieapp.ui.components.DetailScreenTopBar
import com.example.movieapp.ui.components.MovieDetailsTopItem
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.ui.components.common.TextWithIcon
import com.example.movieapp.ui.components.shimmerEffect
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieDetailsViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int?,
    onBackClick: () -> Unit,
    onMovieClick: (movieId: Int) -> Unit,
    onViewAllMoviesClick: (title: String, url: String) -> Unit,
    onPersonClick: (personId: Int) -> Unit,
    onViewAllCreditsClick: (creditResponse: CreditResponse) -> Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        movieId?.let { movieId ->
            viewModel.getMovieDetails(movieId)
            viewModel.getMoviesImages(movieId)
            viewModel.getSimilarMovies(movieId)
            viewModel.getMovieCredits(movieId)
        }
    })
    val scrollState = rememberScrollState()
    println("scroll state" + scrollState.isScrollInProgress)
    val isCollapsed by remember {
        derivedStateOf { scrollState.value > 0 }
    }
    val movieDetailsResponse = viewModel.moviesDetailsResponse.collectAsState()
    val creditResponse = viewModel.creditResponse.collectAsState()
    val similarMoviesResponse = viewModel.similarMoviesResponse.collectAsState()
    MovieDetailsScreen(
        movieDetailsResponse = movieDetailsResponse.value,
        movieImagesResponse = viewModel.movieImagesResponse.collectAsState().value,
        creditResponse = creditResponse.value,
        similarMoviesResponse = similarMoviesResponse.value,
        onBackClick = onBackClick,
        onMovieClick = onMovieClick,
        onViewAllMoviesClick = onViewAllMoviesClick,
        onPersonClick = onPersonClick,
        onViewAllCreditsClick = onViewAllCreditsClick,
        isCollapsed = isCollapsed,
        scrollState
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    movieDetailsResponse: DataHandler<MovieDetailsResponse>,
    movieImagesResponse: DataHandler<MovieImagesResponse>,
    creditResponse: DataHandler<CreditResponse>,
    similarMoviesResponse: DataHandler<MovieResponse>,
    onBackClick: () -> Unit,
    onMovieClick: (movieId: Int) -> Unit,
    onViewAllMoviesClick: (title: String, url: String) -> Unit,
    onPersonClick: (personId: Int) -> Unit,
    onViewAllCreditsClick: (creditResponse: CreditResponse) -> Unit,
    isCollapsed: Boolean,
    scrollState: ScrollState
) {
    when (movieDetailsResponse) {
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
            val movieResponse = movieDetailsResponse.data
//                val movieImagesResponse = viewModel.movieImagesResponse.collectAsState()
            when (movieImagesResponse) {
                is DataHandler.Failure -> {
                }

                DataHandler.Loading -> {
                }

                is DataHandler.Success -> {
                    movieImagesResponse.data.backdrops?.let { images ->
                        Scaffold(topBar = {
                            DetailScreenAppBar(isCollapsed, images, movieResponse.title)
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
                                                TextWithIcon(
                                                    Modifier.padding(top = 10.dp),
                                                    Icons.Filled.DateRange,
                                                    Color.Red,
                                                    movieResponse.releaseDate.toString()
                                                )
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
                                CreditsUIItem(creditResponse, "Cast", itemClick = { personId ->
                                    if (personId != null) {
                                        onPersonClick(personId)
                                    }
                                }) {
                                    onViewAllCreditsClick(it)
                                }
                                Spacer(modifier = Modifier.size(10.dp))
                                MoviesUiItem(similarMoviesResponse,
                                    "Similar Movies",
                                    itemClick = { movieId ->
                                        if (movieId != null) {
                                            onMovieClick(movieId)
                                        }
                                    },
                                    viewAllItemClick = {
                                        onViewAllMoviesClick(
                                            "Upcoming Movies", Constants.URL_UPCOMING_MOVIES
                                        )
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

@Composable
fun DetailScreenAppBar(
    isCollapsed: Boolean, images: List<ImageItem>, title: String?
) {
    AnimatedVisibility(visible = !isCollapsed) {
        MovieDetailsTopItem(imageList = images, title)
    }
    val iconColor: Color by animateColorAsState(
        if (isCollapsed) {
            Color.White
        } else {
            Color.Black
        }
    )
    val iconBgColor: Color by animateColorAsState(
        if (isCollapsed) {
            Color.Transparent
        } else {
            Color.LightGray.copy(0.5f)
        }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),

        ) {
        DetailScreenTopBar(
            onBackClick = { }, iconColor, iconBgColor
        )
    }
}