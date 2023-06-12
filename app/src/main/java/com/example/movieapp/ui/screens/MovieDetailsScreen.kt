package com.example.movieapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.DataHandler
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsScreen(
    viewModel: MovieViewModel = hiltViewModel(), movieId: Int?, navController: NavHostController
) {
    LaunchedEffect(key1 = "", block = {
        movieId?.let { movieId ->
            viewModel.getMovieDetails(movieId)
            viewModel.getMoviesImages(movieId)
            viewModel.getSimilarMovies(movieId)
        }
    })
    val movieDetailsResponse = viewModel.moviesDetailsResponse.value
    val similarMoviesResponse = viewModel.similarMoviesResponse.value

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Movie Details", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        })
    }) { paddingValues ->
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                when (movieDetailsResponse) {
                    is DataHandler.Failure -> TODO()
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

                    else -> {
                        Surface {
                            val movieImagesResponse = viewModel.movieImagesResponse.collectAsState()
                            movieImagesResponse.value.let {
                                when (it) {
                                    is DataHandler.Failure -> {
                                    }

                                    DataHandler.Loading -> {
                                    }

                                    is DataHandler.Success -> {
                                        val pageState =
                                            rememberPagerState { it.data.posters?.size!! }
                                        HorizontalPager(state = pageState) { page ->
                                            Box {
                                                val movie = it.data.posters?.get(page)
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(start = 16.dp, end = 16.dp),
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    AsyncImage(
                                                        modifier = Modifier
                                                            .fillMaxSize()
                                                            .clip(RoundedCornerShape(10.dp)),
                                                        model = movie?.getOriginalImagePath(),
                                                        contentDescription = "",
                                                        contentScale = ContentScale.FillBounds
                                                    )
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
        }
    }

}