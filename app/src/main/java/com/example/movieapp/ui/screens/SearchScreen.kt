package com.example.movieapp.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.ui.components.SearchMovieListItem
import com.example.movieapp.ui.components.SearchPersonListItem
import com.example.movieapp.ui.components.SearchTvListItem
import com.example.movieapp.viewmodel.MediaType
import com.example.movieapp.viewmodel.SearchType
import com.example.movieapp.viewmodel.SearchViewModel
import com.google.gson.Gson

@Composable
fun SearchScreen(
    url: String,
    searchType: String,
    viewModel: SearchViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val searchList by viewModel.searchList.collectAsState()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navHostController.popBackStack()
                    })
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.clearText()
                    }) {
                        Icon(
                            Icons.Default.Close, contentDescription = ""
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(5.dp),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                viewModel.getSearchData(url)
            }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
        )
        Spacer(modifier = Modifier.size(16.dp))
        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Log.e("search", Gson().toJson(searchList))
            items(count = searchList.size) {
                val item = searchList[it]
                when (searchType) {
                    SearchType.MOVIE.value -> {
                        SearchMovieListItem(item, onItemClick = {})
                    }

                    SearchType.TV.value -> {
                        SearchTvListItem(item, onItemClick = {})
                    }

                    SearchType.PERSON.value -> {
                        SearchPersonListItem(item, onItemClick = {})
                    }

                    SearchType.MULTI.value -> {
                        if (!item.mediaType.isNullOrEmpty()) {
                            when (item.mediaType) {
                                MediaType.MOVIE.value -> {
                                    SearchMovieListItem(item, onItemClick = {})
                                }

                                MediaType.TV.value -> {
                                    SearchTvListItem(item, onItemClick = {})
                                }

                                MediaType.PERSON.value -> {
                                    SearchPersonListItem(item, onItemClick = {})
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}