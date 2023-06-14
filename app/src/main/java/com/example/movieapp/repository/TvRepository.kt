package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.network.model.PersonImagesResponse
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getPopularTvShows(pageNo: Int): Flow<MovieResponse>
    fun getTopRatedTvShows(pageNo: Int): Flow<MovieResponse>
    fun getNowPlayingTvShows(pageNo: Int): Flow<MovieResponse>
    fun getUpcomingTvShows(pageNo: Int): Flow<MovieResponse>
    fun getTrendingTvShows(): Flow<MovieResponse>
    fun getAllTvShows(url: String): Flow<PagingData<MovieItem>>
    fun getPersonTvShows(personId: String): Flow<MovieResponse>
    fun getSimilarTvShows(movieId: String): Flow<MovieResponse>
    fun getTvShowDetails(movieId: String): Flow<MovieDetailsResponse>
    fun getTvShowImages(movieId: String): Flow<MovieImagesResponse>
    fun getPersonImages(movieId: String): Flow<PersonImagesResponse>
    fun getMovieCredits(movieId: String): Flow<CreditResponse>
    fun getPersonDetails(personId: String): Flow<PersonDetailResponse>

}