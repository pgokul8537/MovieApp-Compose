package com.example.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.network.model.SearchResponse
import com.example.movieapp.ui.components.common.TextWithIcon

@Composable
fun SearchMovieListItem(item: SearchResponse.SearchItem, onItemClick: (movieId: Int?) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick.invoke(item.id) },
        horizontalAlignment = Alignment.Start
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            AsyncImage(
                modifier = Modifier
                    .width(160.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
                model = item.getDefaultImagePath(),
                contentScale = ContentScale.FillBounds,
                contentDescription = item.title,
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                if (!item.title.isNullOrEmpty()) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 10.dp),
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
                if (item.voteAverage != null && item.voteAverage > 0) {
                    TextWithIcon(
                        modifier = Modifier.padding(top = 10.dp),
                        icon = Icons.Filled.Star,
                        tint = Color.Red,
                        title = item.voteAverage.toString()
                    )
                }
            }
        }
    }
}