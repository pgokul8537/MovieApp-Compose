package com.example.movieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerMovieListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Column(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    } else {
        contentAfterLoading()
    }

}

fun Modifier.shimmerEffect(): Modifier = composed {
    background(
        ShimmerBrush(true, 1300f)
    )
}