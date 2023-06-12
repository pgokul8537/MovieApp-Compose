package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.network.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(pageNo: Int): Flow<MovieResponse>
    fun getTopRatedMovies(pageNo: Int): Flow<MovieResponse>
    fun getNowPlayingMovies(pageNo: Int): Flow<MovieResponse>
    fun getUpcomingMovies(pageNo: Int): Flow<MovieResponse>
    fun getAllMovies(url: String): Flow<PagingData<MovieItem>>
    fun getTrendingMovies(): Flow<MovieResponse>
    fun getMoviesDetails(movieId: String): Flow<MovieDetailsResponse>
    fun getSimilarMovies(movieId: String): Flow<MovieResponse>
    fun getMoviesImages(movieId: String): Flow<MovieImagesResponse>
}