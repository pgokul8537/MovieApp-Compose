package com.example.movieapp.ui.screens

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.movieapp.NavigationRoute
import com.example.movieapp.ui.components.AllMoviesListItem
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieViewAllScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    title: String,
    url: String,
    navHostController: NavHostController
) {
    val pagingItems = viewModel.getAllMovies(url).collectAsLazyPagingItems()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        )
    }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            when (pagingItems.loadState.refresh) {
                is LoadState.Error -> {
                    (pagingItems.loadState.refresh as LoadState.Error).error.also {
                        it.printStackTrace()
                    }.message.let {
                        Text(text = it ?: "Something went wrong")
                    }
                }

                LoadState.Loading -> {
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(160.dp),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        flingBehavior = ScrollableDefaults.flingBehavior(),
                        verticalItemSpacing = 16.dp
                    )
                    {
                        items(count = pagingItems.itemCount, key = pagingItems.itemKey()) {
                            val item = pagingItems[it]
                            item?.let { movieItem ->
                                AllMoviesListItem(item = movieItem, onItemClick = { movieId ->
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
                        if (pagingItems.loadState.append == LoadState.Loading) {
                            item {
                                MovieProgress(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
