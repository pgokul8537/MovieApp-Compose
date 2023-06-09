package com.example.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieapp.network.ApiInterface
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.flow.flow
import okhttp3.internal.format
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiInterface: ApiInterface) : MovieRepository {

    override fun getPopularMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.urlPopularMovies, pageNo = pageNo)) }

    override fun getTopRatedMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.urlTopRatedMovies, pageNo = pageNo)) }

    override fun getNowPlayingMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.urlNowPlayingMovies, pageNo = pageNo)) }

    override fun getUpcomingMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.urlUpcomingMovies, pageNo = pageNo)) }

    override fun getAllMovies(url: String) =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { AllMoviesPagingSource(apiInterface, url) }).flow

    override fun getMoviesDetails(movieId: String) =
        flow { emit(apiInterface.getMoviesDetails(format(Constants.URL_MOVIE_DETAILS, movieId))) }

}