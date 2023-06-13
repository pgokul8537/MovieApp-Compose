package com.example.movieapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.DataHandler
import com.example.movieapp.ui.components.CreditsUIItem
import com.example.movieapp.ui.components.MovieDetailsTopItem
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.viewmodel.MovieViewModel

@OptIn(ExperimentalFoundationApi::class)
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
    val movieDetailsResponse = viewModel.moviesDetailsResponse.collectAsState()
    val similarMoviesResponse = viewModel.similarMoviesResponse.value

    /*Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Movie Details", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        })
    }) { paddingValues ->*/

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
                                                text = "Overview",
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                            Spacer(modifier = Modifier.size(10.dp))
                                            movieResponse.overview?.let { overview ->
                                                Text(
                                                    text = overview,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Normal,
                                                    color = Color.White
                                                )
                                            }
                                            Spacer(modifier = Modifier.size(10.dp))
                                            CreditsUIItem(similarMoviesResponse, "Cast",
                                                itemClick = { movieId ->

                                                },
                                                viewAllItemClick = {

                                                })
                                            Spacer(modifier = Modifier.size(10.dp))
                                            MoviesUiItem(similarMoviesResponse, "Similar Movies",
                                                itemClick = { movieId ->

                                                },
                                                viewAllItemClick = {

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
//    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieDetailsToolbar(onBackClick: () -> Unit) {
    Surface {
        TopAppBar(
            title = {

            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent.copy(alpha = 0.1f)),
            navigationIcon = {
                IconButton(onClick = {
                    onBackClick.invoke()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        )
    }
}