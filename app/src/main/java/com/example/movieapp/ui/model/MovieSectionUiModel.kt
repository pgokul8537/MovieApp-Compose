package com.example.movieapp.ui.model

import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.MovieResponse

data class MovieSectionUiModel(
    val title: String,
    val moviesResponse: DataHandler<MovieResponse>,
    val viewAllMoviesUrl: String,
)
