package com.example.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.MovieResponse

@Composable
fun CreditsUIItem(
    popularMoviesResponse: DataHandler<MovieResponse>,
    title: String,
    itemClick: (movieId: Int?) -> Unit,
    viewAllItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
            Surface(
                modifier = Modifier,
                shape = CircleShape,
                color = Color.Red.copy(alpha = 0.5f)
            ) {
                Text(
                    text = "View All",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                        .clickable {
                            viewAllItemClick.invoke()
                        },
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
            }

        }

        when (popularMoviesResponse) {
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
                val contentPadding = WindowInsets.navigationBars.add(
                    WindowInsets(
                        left = 16.dp,
                        right = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                ).asPaddingValues()
                if (!popularMoviesResponse.data.results.isNullOrEmpty()) {
                    LazyRow(
                        contentPadding = contentPadding,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        content = {
                            items(count = popularMoviesResponse.data.results.size) {
                                val item = popularMoviesResponse.data.results[it]
                                if (item != null) {
                                    MovieListItem(item, onItemClick = { movieId ->
                                        itemClick.invoke(movieId)
                                    })
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}