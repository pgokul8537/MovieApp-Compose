package com.example.movieapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.movieapp.network.model.CreditResponse

@Composable
fun AllCreditsListItem(
    creditList: List<CreditResponse.CreditItem>,
    onItemClick: (movieId: Int?) -> Unit
) {
    val showShimmer = remember { mutableStateOf(true) }
    Surface {
        val contentPadding = WindowInsets.navigationBars.add(
            WindowInsets(
                left = 16.dp,
                right = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
        ).asPaddingValues()
        LazyRow(
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = creditList.size) {
                    val item = creditList[it]
                    if (item != null) {
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .wrapContentHeight()
                                .wrapContentHeight()
                                .clickable { onItemClick.invoke(item.id) },
                            horizontalAlignment = Alignment.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White, CircleShape),
                            ) {
                                AsyncImage(
                                    model = item.getDefaultImagePath(),
                                    contentScale = ContentScale.FillBounds,
                                    contentDescription = item.name,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            if (!item.name.isNullOrEmpty()) {
                                Text(
                                    text = item.name!!,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth(),
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                            if (!item.character.isNullOrEmpty()) {
                                Text(
                                    text = "(${item.character!!})",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth(),
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        )
    }


}