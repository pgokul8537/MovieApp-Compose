package com.example.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.NavigationGraph
import com.example.movieapp.ui.components.BottomNavigation
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.MovieViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MovieViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigation(navController = navController) },
                    content = { paddingValues ->
                       /* val systemUiController = rememberSystemUiController()
                        systemUiController.setStatusBarColor(Color.Transparent)
                        systemUiController.setNavigationBarColor(Color.Transparent)
                        systemUiController.setSystemBarsColor(Color.Black)
                        systemUiController.isStatusBarVisible = false // Status bar
                        systemUiController.isNavigationBarVisible = false // Navigation bar
                        systemUiController.isSystemBarsVisible = false // Status & Navigation bars
                        systemUiController.navigationBarDarkContentEnabled = false*/
                        Surface(modifier = Modifier.padding(paddingValues)) {
                            NavigationGraph(navController = navController)
                        }
                    })
            }
        }

    }

}
