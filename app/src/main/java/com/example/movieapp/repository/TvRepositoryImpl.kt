package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.ApiInterface
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.network.model.PersonImagesResponse
import com.example.movieapp.network.model.TvShowsResponse
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.internal.format
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : TvRepository {
    override fun getPopularTvShows(pageNo: Int) =
        flow { emit(apiInterface.getTvShows(Constants.URL_POPULAR_TV, pageNo = pageNo)) }

    override fun getTopRatedTvShows(pageNo: Int) =
        flow { emit(apiInterface.getTvShows(Constants.URL_TOP_RATED_TV, pageNo = pageNo)) }

    override fun getOnTheAirTvShows(pageNo: Int) =
        flow { emit(apiInterface.getTvShows(Constants.URL_AIRING_TODAY_TV, pageNo = pageNo)) }

    override fun getAiringTodayTvShows(pageNo: Int) =
        flow { emit(apiInterface.getTvShows(Constants.URL_AIRING_TODAY_TV, pageNo = pageNo)) }


    override fun getTrendingTvShows(pageNo: Int) =
        flow { emit(apiInterface.getTvShows(Constants.URL_TRENDING_TV, pageNo = pageNo)) }


    override fun getAllTvShows(url: String): Flow<PagingData<MovieItem>> {
        TODO()
    }

    override fun getPersonTvShows(personId: String): Flow<TvShowsResponse> {
        TODO()
    }

    override fun getSimilarTvShows(movieId: String) =
        flow { emit(apiInterface.getTvShows(format(Constants.URL_SIMILAR_TV, movieId), pageNo = 1)) }

    override fun getTvShowDetails(movieId: String) =
        flow { emit(apiInterface.getTvShowDetails(format(Constants.URL_TV_DETAILS, movieId))) }

    override fun getTvShowImages(movieId: String) =
        flow { emit(apiInterface.getMoviesImages(format(Constants.URL_TV_IMAGES, movieId))) }

    override fun getPersonImages(movieId: String): Flow<PersonImagesResponse> {
        TODO()
    }

    override fun getTvCredits(movieId: String) =
        flow { emit(apiInterface.getMovieCredits(format(Constants.URL_CREDITS_TV, movieId))) }

    override fun getPersonDetails(personId: String): Flow<PersonDetailResponse> {
        TODO()
    }
}