package com.example.movieapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.network.model.MovieItem

@Composable
fun AllMoviesListItem(item: MovieItem, onItemClick: (movieId: Int?) -> Unit) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .wrapContentHeight()
            .wrapContentHeight()
            .clickable { onItemClick.invoke(item.id) },
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(Color.Gray)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${item.posterPath}",
                contentScale = ContentScale.FillBounds,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (!item.title.isNullOrEmpty()) {
            Text(
                text = item.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }
        if (item.voteAverage != null && item.voteAverage > 0) {
            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = Color.Red,
                    contentDescription = "rating"
                )
                Text(
                    text = item.voteAverage.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }

        }
    }

}