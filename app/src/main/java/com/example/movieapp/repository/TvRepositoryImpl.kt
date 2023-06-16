package com.example.movieapp.repository

import androidx.paging.PagingData
import com.example.movieapp.network.ApiInterface
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.network.model.PersonImagesResponse
import com.example.movieapp.network.model.TvShowsResponse
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
        TODO("Not yet implemented")
    }

    override fun getPersonTvShows(personId: String): Flow<TvShowsResponse> {
        TODO("Not yet implemented")
    }

    override fun getSimilarTvShows(movieId: String): Flow<TvShowsResponse> {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetails(movieId: String): Flow<MovieDetailsResponse> {
        TODO("Not yet implemented")
    }

    override fun getTvShowImages(movieId: String): Flow<MovieImagesResponse> {
        TODO("Not yet implemented")
    }

    override fun getPersonImages(movieId: String): Flow<PersonImagesResponse> {
        TODO("Not yet implemented")
    }

    override fun getMovieCredits(movieId: String): Flow<CreditResponse> {
        TODO("Not yet implemented")
    }

    override fun getPersonDetails(personId: String): Flow<PersonDetailResponse> {
        TODO("Not yet implemented")
    }
}