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

interface DiscoverRepository {
    fun getPopularMovies(pageNo: Int): Flow<MovieResponse>

}