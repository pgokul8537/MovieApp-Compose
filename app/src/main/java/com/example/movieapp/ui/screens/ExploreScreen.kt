package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.NavigationRoute
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.MovieViewModel
import com.example.movieapp.viewmodel.SearchType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    viewModel: MovieViewModel = hiltViewModel(), navHostController: NavHostController
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Explore", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        }, actions = {
            IconButton(onClick = {
                navHostController.apply {
                    currentBackStackEntry?.savedStateHandle?.apply {
                        set("url", Constants.URL_SEARCH_MULTI)
                        set("search_type", SearchType.MULTI.value)
                    }
                    navigate(NavigationRoute.SEARCH.route)
                }
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
        })
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {

            Text(
                text = "Explore",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }

}