package com.example.movieapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.ui.components.common.DotsIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithImage(images: List<String>, onItemClick: (movieId: Int?) -> Unit) {
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState { images.size }
    val isDragged by pageState.interactionSource.collectIsDraggedAsState()
    /*LaunchedEffect(Unit) {
        scope.launch {
            delay(3000)
            pageState.animateScrollToPage(
                (pageState.currentPage + 1)
            )
        }
    }*/
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        HorizontalPager(
            state = pageState,
        ) { page ->
            Surface {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                        .clickable {
                            onItemClick.invoke(page)
                        },
                    model = images[page],
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
                totalDots = images.size,
                selectedIndex = if (isDragged) pageState.currentPage else pageState.targetPage,
                selectedColor = Color.Red, unSelectedColor = Color.White,
                dotSize = 8.dp
            )
        }
    }
}