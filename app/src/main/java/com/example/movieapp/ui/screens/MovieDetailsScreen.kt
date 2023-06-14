package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.movieapp.ui.components.MovieDetailsTopItem
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.ui.components.TextWithIcon
import com.example.movieapp.ui.components.shimmerEffect
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    viewModel: MovieViewModel = hiltViewModel(), movieId: Int?, navHostController: NavHostController
) {
    LaunchedEffect(key1 = "", block = {
        movieId?.let { movieId ->
            viewModel.getMovieDetails(movieId)
            viewModel.getMoviesImages(movieId)
            viewModel.getSimilarMovies(movieId)
            viewModel.getMovieCredits(movieId)
        }
    })
    val movieDetailsResponse = viewModel.moviesDetailsResponse.collectAsState()
    val creditResponse = viewModel.creditResponse.collectAsState()
    val similarMoviesResponse = viewModel.similarMoviesResponse.value
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = PaddingValues())
                .verticalScroll(rememberScrollState())
        ) {

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
                        val movieImagesResponse = viewModel.movieImagesResponse.collectAsState()
                        movieImagesResponse.value.let {
                            when (it) {
                                is DataHandler.Failure -> {
                                }

                                DataHandler.Loading -> {
                                }

                                is DataHandler.Success -> {
                                    it.data.posters?.let { images ->
                                        MovieDetailsTopItem(
                                            images, movieResponse
                                        )
                                    }
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        movieResponse.title?.let { title ->
                                            Text(
                                                text = title,
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                        }
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
                                                                    modifier = Modifier
                                                                        .padding(
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
                                                        Icons.Filled.Star,
                                                        Color.Red,
                                                        movieResponse.voteAverage.toString()
                                                    )
                                                    Spacer(modifier = Modifier.size(10.dp))
                                                    TextWithIcon(
                                                        Icons.Filled.DateRange,
                                                        Color.White,
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
                                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                            text = overview,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(10.dp))
                                    CreditsUIItem(creditResponse, "Cast",
                                        itemClick = { movieId ->
                                            navHostController.apply {
                                                currentBackStackEntry?.savedStateHandle?.set(
                                                    "movie_id",
                                                    movieId
                                                )
                                                navigate(NavigationRoute.PERSON_DETAILS.route)
                                            }
                                        }, viewAllItemClick = {
                                            navHostController.apply {
                                                currentBackStackEntry?.savedStateHandle?.set(
                                                    "credit_response",
                                                    it
                                                )
                                                navigate(NavigationRoute.CREDITS_VIEW_ALL.route)
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    MoviesUiItem(similarMoviesResponse, "Similar Movies",
                                        itemClick = { movieId ->
                                            navHostController.apply {
                                                currentBackStackEntry?.savedStateHandle?.set(
                                                    "movie_id",
                                                    movieId
                                                )
                                                navigate(NavigationRoute.MOVIE_DETAILS.route)
                                            }
                                        },
                                        viewAllItemClick = {
                                            navHostController.apply {
                                                currentBackStackEntry?.savedStateHandle?.apply {
                                                    set("title", "Upcoming Movies")
                                                    set(
                                                        "url",
                                                        Constants.URL_UPCOMING_MOVIES
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),

            ) {
            MovieDetailsToolbar(onBackClick = {
                navHostController.popBackStack()
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieDetailsToolbar(onBackClick: () -> Unit) {
    val iconModifier = Modifier
        .size(50.dp)
        .background(
            color = Color.LightGray.copy(0.5f),
            shape = CircleShape
        )
    TopAppBar(
        title = {
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent.copy(alpha = 0.1f)),
        navigationIcon = {
            IconButton(modifier = iconModifier, onClick = {
                onBackClick.invoke()
            }) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }
        }
    )
}