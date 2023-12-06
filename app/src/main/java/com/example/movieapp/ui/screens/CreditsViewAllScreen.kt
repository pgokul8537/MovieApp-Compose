package com.example.movieapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.movieapp.NavigationRoute
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.ui.components.AllCreditsListItem
import com.example.movieapp.utils.Constants
import com.example.movieapp.viewmodel.SearchType
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreditsViewAllScreen(
    creditResponse: CreditResponse,
    pages: Array<CreditsPage> = CreditsPage.values(),
    onPersonClick: (personId: Int) -> Unit,
    onBackClick: () -> Unit,
    onSearchClick: (type: String, url: String) -> Unit,
) {
    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(pagerState.currentPage) {

    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Credits", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = {
                onBackClick.invoke()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
        }, actions = {
            IconButton(onClick = {
                onSearchClick.invoke(SearchType.PERSON.value,Constants.URL_SEARCH_PERSON)

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
            Column {
                TabRow(pagerState.currentPage) {
                    pages.forEachIndexed { index, page ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                            text = {

                                Text(
                                    text = page.name,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 16.sp
                                )
                            },
                            unselectedContentColor = Color.White,
                            selectedContentColor = Color.Red
                        )
                    }
                }
                HorizontalPager(state = pagerState) { index ->
                    when (pages[index]) {
                        CreditsPage.CAST -> {
                            creditResponse.cast?.let { list ->
                                AllCreditsListItem(list, onItemClick = { personId ->
                                    personId?.let {
                                        onPersonClick(it)
                                    }
                                })
                            }
                        }

                        CreditsPage.CREW -> {
                            creditResponse.crew?.let { list ->
                                AllCreditsListItem(list, onItemClick = { personId ->
                                    personId?.let {
                                        onPersonClick(it)
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class CreditsPage(
    name: String
) {
    CAST("Cast"), CREW("Crew")
}