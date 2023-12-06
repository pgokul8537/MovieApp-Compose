package com.example.movieapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.NavigationRoute
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    viewModel: MovieViewModel = hiltViewModel(), onSearchClick: (type: String, url: String) -> Unit,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Explore", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, actions = {
            IconButton(onClick = {
                onSearchClick.invoke(NavigationRoute.SEARCH.route, Constants.URL_SEARCH_MULTI)
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
        })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it), contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Search Movies, Tv shows, Cast, Crew... ",
                fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 30.sp
            )
        }
    }
    val context = LocalContext.current
    BackHandler {
        context.findActivity()?.finish()
    }
}