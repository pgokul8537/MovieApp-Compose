package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.TvShowDetailsResponse
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.network.model.PersonImagesResponse
import com.example.movieapp.network.model.TvShowsResponse
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getPopularTvShows(pageNo: Int): Flow<TvShowsResponse>
    fun getTopRatedTvShows(pageNo: Int): Flow<TvShowsResponse>
    fun getOnTheAirTvShows(pageNo: Int): Flow<TvShowsResponse>
    fun getAiringTodayTvShows(pageNo: Int): Flow<TvShowsResponse>
    fun getTrendingTvShows(pageNo: Int): Flow<TvShowsResponse>
    fun getAllTvShows(url: String): Flow<PagingData<MovieItem>>
    fun getPersonTvShows(personId: String): Flow<TvShowsResponse>
    fun getSimilarTvShows(movieId: String): Flow<TvShowsResponse>
    fun getTvShowDetails(movieId: String): Flow<TvShowDetailsResponse>
    fun getTvShowImages(movieId: String): Flow<MovieImagesResponse>
    fun getPersonImages(movieId: String): Flow<PersonImagesResponse>
    fun getTvCredits(movieId: String): Flow<CreditResponse>
    fun getPersonDetails(personId: String): Flow<PersonDetailResponse>

}