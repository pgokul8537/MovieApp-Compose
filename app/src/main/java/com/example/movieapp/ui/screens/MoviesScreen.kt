package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.ui.components.HorizontalPagerWithImage
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.ui.model.MovieSectionUiModel
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MoviesScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (movieId: Int) -> Unit,
    onViewAllMoviesClick: (title: String, url: String) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Black,
            darkIcons = false
        )
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getPopularMovies(1)
        viewModel.getNowPlayingMovies(1)
        viewModel.getTopRatedMovies(1)
        viewModel.getUpcomingMovies(1)
        viewModel.getTrendingMovies()

    })
    val popularMoviesResponse = viewModel.popularMoviesResponse.collectAsState()
    val topRatedMoviesResponse = viewModel.topRatedMoviesResponse.collectAsState()
    val upcomingMoviesResponse = viewModel.upcomingMoviesResponse.collectAsState()
    val nowPlayingMoviesResponse = viewModel.nowPlayingMoviesResponse.collectAsState()
    val trendingMoviesResponse = viewModel.trendingMoviesResponse.collectAsState()

    MoviesScreen(
        popularMoviesResponse = popularMoviesResponse.value,
        topRatedMoviesResponse = topRatedMoviesResponse.value,
        upcomingMoviesResponse = upcomingMoviesResponse.value,
        nowPlayingMoviesResponse = nowPlayingMoviesResponse.value,
        trendingMoviesResponse = trendingMoviesResponse.value,
        onMovieClick = onMovieClick,
        onViewAllMoviesClick = onViewAllMoviesClick
    )
}

@Composable
fun MoviesScreen(
    popularMoviesResponse: DataHandler<MovieResponse>,
    topRatedMoviesResponse: DataHandler<MovieResponse>,
    upcomingMoviesResponse: DataHandler<MovieResponse>,
    nowPlayingMoviesResponse: DataHandler<MovieResponse>,
    trendingMoviesResponse: DataHandler<MovieResponse>,
    onMovieClick: (movieId: Int) -> Unit,
    onViewAllMoviesClick: (title: String, url: String) -> Unit
) {

    val sections = listOf(
        MovieSectionUiModel(
            "Upcoming", upcomingMoviesResponse, Constants.URL_UPCOMING_MOVIES
        ),
        MovieSectionUiModel(
            "Popular", popularMoviesResponse, Constants.URL_POPULAR_MOVIES
        ),
        MovieSectionUiModel(
            "Top Rated", topRatedMoviesResponse, Constants.URL_TOP_RATED_MOVIES
        ),
        MovieSectionUiModel(
            "Now Playing", nowPlayingMoviesResponse, Constants.URL_NOW_PLAYING_MOVIES
        ),
    )

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val contentPadding = WindowInsets.navigationBars.add(
            WindowInsets(
                bottom = 16.dp
            )
        ).asPaddingValues()
        LazyColumn(contentPadding = contentPadding, content = {
            item {
                MoviesImagePager(
                    trendingMoviesResponse, onMovieClick
                )
            }
            items(sections) {
                MoviesUiItem(it.moviesResponse, it.title, itemClick = { movieId ->
                    if (movieId != null) {
                        onMovieClick(movieId)
                    }
                }, viewAllItemClick = {
                    onViewAllMoviesClick(
                        "${it.title} Movies", it.viewAllMoviesUrl
                    )
                })
            }
        })
    }
}

@Composable
fun MoviesImagePager(
    moviesResponse: DataHandler<MovieResponse>,
    onMovieClick: (movieId: Int) -> Unit,
) {
    when (moviesResponse) {
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
            val movieList = moviesResponse.data.results
            movieList?.let { list ->
                HorizontalPagerWithImage(images = list.mapNotNull { it.getOriginalImagePath() },
                    onItemClick = { index ->
                        if (index != null) {
                            list.get(index).id?.let(onMovieClick)
                        }
                    })
            }
        }
    }
}/*@Composable
fun MoviesScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Black,
            darkIcons = false
        )
    }
    LaunchedEffect(key1 = "", block = {
        viewModel.getPopularMovies(1)
        viewModel.getNowPlayingMovies(1)
        viewModel.getTopRatedMovies(1)
        viewModel.getUpcomingMovies(1)
        viewModel.getTrendingMovies()

    })
    val scrollState = rememberScrollState()
    val popularMoviesResponse = viewModel.popularMoviesResponse.collectAsState()
    val topRatedMoviesResponse = viewModel.topRatedMoviesResponse.collectAsState()
    val upcomingMoviesResponse = viewModel.upcomingMoviesResponse.collectAsState()
    val nowPlayingMoviesResponse = viewModel.nowPlayingMoviesResponse.collectAsState()
    val trendingMoviesResponse = viewModel.trendingMoviesResponse.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            trendingMoviesResponse.value.let {
                when (it) {
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
                        val movieList = it.data.results
                        movieList?.let { list ->
                            HorizontalPagerWithImage(
                                movieItem = list,
                                onItemClick = { movieId ->
                                    navHostController.apply {
                                        currentBackStackEntry?.savedStateHandle?.set(
                                            "movie_id",
                                            movieId
                                        )
                                        navigate(NavigationRoute.MOVIE_DETAILS.route)
                                    }
                                })
                        }
                    }
                }
            }
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
                            set("url", Constants.URL_UPCOMING_MOVIES)
                        }
                        navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                    }
                })
            MoviesUiItem(popularMoviesResponse, "Popular",
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
                            set("url", Constants.URL_POPULAR_MOVIES)
                        }
                        navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                    }
                })
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
                            set("url", Constants.URL_TOP_RATED_MOVIES)
                        }
                        navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                    }
                })

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
                            set("url", Constants.URL_NOW_PLAYING_MOVIES)
                        }
                        navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                    }
                })
        }
    }
}*/

@Composable
fun Progress() {
    CircularProgressIndicator()
}