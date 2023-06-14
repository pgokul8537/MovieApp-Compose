package com.example.movieapp.network

import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.network.model.PersonImagesResponse
import com.example.movieapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {


    @GET
    suspend fun getMovies(@Url url: String, @Query("page") pageNo: Int): MovieResponse

    @GET
    suspend fun getMoviesImages(@Url url: String): MovieImagesResponse
    @GET
    suspend fun getPersonImages(@Url url: String): PersonImagesResponse

    @GET
    suspend fun getMoviesDetails(@Url url: String): MovieDetailsResponse

    @GET
    suspend fun getPersonDetails(@Url url: String): PersonDetailResponse

    @GET(Constants.URL_TRENDING_MOVIES)
    suspend fun getTrendingMovies(): MovieResponse

    @GET
    suspend fun getMovieCredits(@Url url: String): CreditResponse
}