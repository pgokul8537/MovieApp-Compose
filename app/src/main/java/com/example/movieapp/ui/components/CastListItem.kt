package com.example.movieapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun CastListItem(item: CreditResponse.Cast?, onItemClick: (movieId: Int?) -> Unit) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(220.dp)
            .clickable { onItemClick.invoke(item?.id) }
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
        ) {
            AsyncImage(
                model = item?.getDefaultImagePath(),
                contentScale = ContentScale.FillBounds,
                contentDescription = item?.name,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (!item?.character.isNullOrEmpty()) {
            Text(
                text = item?.character!!,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
        /*if (!item.title.isNullOrEmpty()) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.White,
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
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }

        }*/
    }

}