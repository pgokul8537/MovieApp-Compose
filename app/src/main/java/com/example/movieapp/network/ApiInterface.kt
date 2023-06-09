package com.example.movieapp.network

import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")
        pageNo: Int
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")
        pageNo: Int
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page")
        pageNo: Int
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") pageNo: Int): MovieResponse

    @GET
    suspend fun getMovies(@Url url: String, @Query("page") pageNo: Int): MovieResponse

    @GET
    suspend fun getMoviesDetails(@Url url: String): MovieDetailsResponse
}