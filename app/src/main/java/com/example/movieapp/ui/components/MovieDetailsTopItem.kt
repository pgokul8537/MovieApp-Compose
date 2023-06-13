package com.example.movieapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsTopItem(
    imageList: List<MovieImagesResponse.MovieImage>,
    movieDetailsResponse: MovieDetailsResponse
) {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            val pageState =
                rememberPagerState { imageList.size }
            HorizontalPager(state = pageState) { page ->
                Surface {
                    val movie = imageList[page]
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect(),
                        model = movie.getOriginalImagePath(),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )
                }

            }
        }
        /*Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.White,
                            Color.White
                        )
                    )
                ), contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                movieDetailsResponse.title?.let {
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

            }
        }*/
    }
}