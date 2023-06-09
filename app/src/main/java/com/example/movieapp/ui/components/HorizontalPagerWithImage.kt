package com.example.movieapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.network.model.MovieItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithImage(movieItem: List<MovieItem>) {
    val pageState = rememberPagerState { movieItem.size }
    val scope = rememberCoroutineScope()
    val isDragged by pageState.interactionSource.collectIsDraggedAsState()
    var pageSize by remember { mutableStateOf(IntSize.Zero) }
    val lastIndex by remember(pageState.currentPage) {
        derivedStateOf { pageState.currentPage == movieItem.size - 1 }
    }
    /*LaunchedEffect(Unit) {
        scope.launch {
            delay(3000)
            pageState.animateScrollToPage(
                (pageState.currentPage + 1) % movieItem.size
            )
        }
    }*/

    Box {
        HorizontalPager(
            state = pageState,
            contentPadding = PaddingValues(start = 5.dp, end = 5.dp)
        ) { page ->
            val movie = movieItem[page]
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
                    model = movie.getOriginalImagePath(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = movieItem.size,
                selectedIndex = if (isDragged) pageState.currentPage else pageState.targetPage,
                selectedColor = Color.Red, unSelectedColor = Color.White,
                dotSize = 8.dp
            )
        }
    }
}