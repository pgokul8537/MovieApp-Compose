package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.DataHandler
import com.example.movieapp.NavigationRoute
import com.example.movieapp.ui.components.DetailScreenTopBar
import com.example.movieapp.ui.components.MovieProgress
import com.example.movieapp.ui.components.MoviesUiItem
import com.example.movieapp.ui.components.common.TextWithIcon
import com.example.movieapp.ui.components.shimmerEffect
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun PersonDetailsScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    personId: Int?,
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = "", block = {
        personId?.let { personId ->
            viewModel.getPersonDetails(personId)
            viewModel.getPersonImages(personId)
            viewModel.getPersonMovies(personId)
        }
    })
    val personDetailResponse = viewModel.personDetailResponse.collectAsState()
    val personImagesResponse = viewModel.personImagesResponse.collectAsState()
    val personMoviesResponse = viewModel.personMoviesResponse.collectAsState()
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = PaddingValues())
                .verticalScroll(rememberScrollState())
        ) {
            personDetailResponse.value.let { result ->
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
                        val personDetails = result.data
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .shimmerEffect(),
                            model = personDetails.getOriginalImagePath(),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        personDetails.name?.let { name ->
                            Text(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                text = name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        personDetails.placeOfBirth?.let {
                            TextWithIcon(
                                Modifier.padding(start = 16.dp, top = 10.dp),
                                Icons.Filled.LocationOn,
                                Color.Red,
                                it
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        personDetails.birthday?.let {
                            TextWithIcon(
                                Modifier.padding(start = 16.dp, top = 10.dp),
                                Icons.Filled.DateRange,
                                Color.Red,
                                it
                            )
                        }
                        personDetails.biography?.let { bio ->
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                text = "Biography",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                text = bio,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                        }

                        personImagesResponse.value.let { result ->
                            when (result) {
                                is DataHandler.Failure -> {
                                }

                                DataHandler.Loading -> {
                                }

                                is DataHandler.Success -> {
                                    val imagesList = result.data.profiles
                                    if (!imagesList.isNullOrEmpty()) {
                                        Spacer(modifier = Modifier.size(10.dp))
                                        Text(
                                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                            text = "Photos",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                        val contentPadding = WindowInsets.navigationBars.add(
                                            WindowInsets(
                                                left = 16.dp,
                                                right = 16.dp,
                                                top = 16.dp,
                                                bottom = 16.dp
                                            )
                                        ).asPaddingValues()
                                        LazyRow(contentPadding = contentPadding,
                                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                                            content = {
                                                items(count = imagesList.size) {
                                                    val item = imagesList[it]
                                                    if (item != null) {
                                                        AsyncImage(
                                                            modifier = Modifier
                                                                .width(160.dp)
                                                                .height(240.dp)
                                                                .clip(RoundedCornerShape(10.dp))
                                                                .shimmerEffect(),
                                                            model = item.getDefaultImagePath(),
                                                            contentScale = ContentScale.FillBounds,
                                                            contentDescription = null,
                                                        )
                                                    }
                                                }
                                            })
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.size(10.dp))
                        MoviesUiItem(personMoviesResponse.value, "Movies", itemClick = { movieId ->
                            navHostController.apply {
                                currentBackStackEntry?.savedStateHandle?.set(
                                    "movie_id", movieId
                                )
                                navigate(NavigationRoute.MOVIE_DETAILS.route)
                            }
                        }, viewAllItemClick = {
                            navHostController.apply {
                                currentBackStackEntry?.savedStateHandle?.apply {
                                    set("title", "Upcoming Movies")
                                    set(
                                        "url", Constants.URL_UPCOMING_MOVIES
                                    )
                                }
                                navigate(NavigationRoute.MOVIES_VIEW_ALL.route)
                            }
                        }, toShowViewAll = false
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),

            ) {
            DetailScreenTopBar(onBackClick = {
                navHostController.popBackStack()
            }, Color.Black, Color.LightGray.copy(0.5f))
        }
    }
}