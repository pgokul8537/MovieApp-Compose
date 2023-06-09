package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.network.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(pageNo: Int): Flow<MovieResponse>
    fun getTopRatedMovies(pageNo: Int): Flow<MovieResponse>
    fun getNowPlayingMovies(pageNo: Int): Flow<MovieResponse>
    fun getUpcomingMovies(pageNo: Int): Flow<MovieResponse>
    fun getAllMovies(url: String): Flow<PagingData<MovieItem>>
    fun getMoviesDetails(movieId: String): Flow<MovieDetailsResponse>
}