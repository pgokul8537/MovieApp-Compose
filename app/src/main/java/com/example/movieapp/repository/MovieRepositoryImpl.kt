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
        flow { emit(apiInterface.getMovies(Constants.URL_POPULAR_MOVIES, pageNo = pageNo)) }

    override fun getTopRatedMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.URL_TOP_RATED_MOVIES, pageNo = pageNo)) }

    override fun getNowPlayingMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.URL_NOW_PLAYING_MOVIES, pageNo = pageNo)) }

    override fun getUpcomingMovies(pageNo: Int) =
        flow { emit(apiInterface.getMovies(Constants.URL_UPCOMING_MOVIES, pageNo = pageNo)) }

    override fun getAllMovies(url: String) = Pager(config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { AllMoviesPagingSource(apiInterface, url) }).flow

    override fun getTrendingMovies() = flow { emit(apiInterface.getTrendingMovies()) }

    override fun getMoviesDetails(movieId: String) =
        flow { emit(apiInterface.getMoviesDetails(format(Constants.URL_MOVIE_DETAILS, movieId))) }

    override fun getMoviesImages(movieId: String) =
        flow { emit(apiInterface.getMoviesImages(format(Constants.URL_MOVIE_IMAGES, movieId))) }

    override fun getPersonImages(movieId: String) =
        flow { emit(apiInterface.getPersonImages(format(Constants.URL_PERSON_IMAGES, movieId))) }

    override fun getSimilarMovies(movieId: String) =
        flow { emit(apiInterface.getMovies(format(Constants.URL_SIMILAR_MOVIES, movieId), 1)) }

    override fun getMovieCredits(movieId: String) =
        flow { emit(apiInterface.getMovieCredits(format(Constants.URL_CREDITS_MOVIES, movieId))) }

    override fun getPersonMovies(personId: String) =
        flow { emit(apiInterface.getPersonMovies(pageNo = 1, personId = personId)) }

    override fun getMultiSearchData(query: String) = flow {
        emit(
            apiInterface.getMultiSearchData(
                url = Constants.URL_MULTI_SEARCH,
                pageNo = 1,
                query = query
            )
        )
    }

    override fun getPersonDetails(personId: String) =
        flow { emit(apiInterface.getPersonDetails(format(Constants.URL_PERSON_DETAILS, personId))) }

}