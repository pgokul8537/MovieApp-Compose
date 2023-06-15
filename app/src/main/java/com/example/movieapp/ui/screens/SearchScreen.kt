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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.viewmodel.MediaType
import com.example.movieapp.viewmodel.SearchViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(), navHostController: NavHostController
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

        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }, leadingIcon = {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navHostController.popBackStack()
                    }
                )
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
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                viewModel.getSearchData()
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
                if (!item.mediaType.isNullOrEmpty()) {
                    when (item.mediaType) {
                        MediaType.MOVIE.value -> {
                            Text(text = "${item.title}")
                        }

                        MediaType.TV.value -> {
                            Text(text = "${item.title}")
                        }

                        MediaType.PERSON.value -> {
                            Text(text = "${item.name}")
                        }
                    }
                }
            }
        }
    }
}