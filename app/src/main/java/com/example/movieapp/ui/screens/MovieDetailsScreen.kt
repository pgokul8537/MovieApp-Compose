package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(movieId: Int?, navController: NavHostController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Movie Details",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        })
    }) { paddingValues ->
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                Text(
                    text = "Movie Details",
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }

}