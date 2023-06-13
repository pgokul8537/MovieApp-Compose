package com.example.movieapp.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.CreditResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreditsViewAllScreen(
    creditResponse: State<DataHandler<CreditResponse>>,
    pages: Array<CreditsPage> = CreditsPage.values(),
    navHostController: NavHostController
) {
//    val pages = listOf("Cast", "Crew")
    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(pagerState.currentPage) {

    }
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            pagerState.currentPage/*, indicator = { tabPositions ->
                TabRowDefaults.Indicator(color = Color.Red)
            }*/
        ) {
            creditResponse.value.let {
                when (it) {
                    is DataHandler.Failure -> {
                    }

                    DataHandler.Loading -> {
                    }

                    is DataHandler.Success -> {
                        val response = it.data
                        Log.e("response", Gson().toJson(response))
                    }
                }
            }
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
                    Text(
                        text = "Cast",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }

                CreditsPage.CREW -> {
                    Text(
                        text = "Crew",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }
            }
            /*creditResponse.value.let {
                when (it) {
                    is DataHandler.Failure -> {

                    }

                    DataHandler.Loading -> {}
                    is DataHandler.Success -> {
                        val response = it.data
                        when (pages[index]) {
                            CreditsPage.CAST -> {
                                response.cast?.let { list ->
                                    AllCreditsListItem(list, onItemClick = {

                                    })
                                }
                            }

                            CreditsPage.CREW -> {
                                response.crew?.let { list ->
                                    AllCreditsListItem(list, onItemClick = {

                                    })
                                }
                            }
                        }
                    }
                }
            }*/

        }

    }
}

enum class CreditsPage(
    name: String
) {
    CAST("Cast"),
    CREW("Crew")
}