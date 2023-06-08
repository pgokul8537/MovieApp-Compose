package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.ApiInterface
import com.example.movieapp.network.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiInterface: ApiInterface) : MovieRepository {

    override fun getPopularMovies(pageNo: Int) =
        flow { emit(apiInterface.getPopularMovies(pageNo = pageNo)) }

    override fun getTopRatedMovies(pageNo: Int) =
        flow { emit(apiInterface.getTopRatedMovies(pageNo = pageNo)) }

    override fun getNowPlayingMovies(pageNo: Int) =
        flow { emit(apiInterface.getNowPlayingMovies(pageNo = pageNo)) }

    override fun getUpcomingMovies(pageNo: Int) =
        flow { emit(apiInterface.getUpcomingMovies(pageNo = pageNo)) }

    override fun getAllMovies(pageNo: Int): Flow<PagingData<MovieResponse>> {
        TODO("Not yet implemented")
    }

}