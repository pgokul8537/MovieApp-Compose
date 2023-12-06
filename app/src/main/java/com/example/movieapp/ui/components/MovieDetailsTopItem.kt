package com.example.movieapp.ui.components

import android.util.Size
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.network.model.ImageItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsTopItem(
    imageList: List<ImageItem>,
    title: String? = null, size: Size = Size(300, 500)
) {
    val pageState =
        rememberPagerState { imageList.size }
    HorizontalPager(state = pageState) { page ->
        Surface {
            val movie = imageList[page]
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.height.dp)
                    .shimmerEffect(),
                model = movie.getOriginalImagePath(),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.height.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    ), verticalArrangement = Arrangement.Bottom
            ) {
                title?.let {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = it,
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}