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

class TvRepositoryImpl: TvRepository {
    override fun getPopularTvShows(pageNo: Int): Flow<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getTopRatedTvShows(pageNo: Int): Flow<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingTvShows(pageNo: Int): Flow<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getUpcomingTvShows(pageNo: Int): Flow<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getTrendingTvShows(): Flow<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getAllTvShows(url: String): Flow<PagingData<MovieItem>> {
        TODO("Not yet implemented")
    }

    override fun getPersonTvShows(personId: String): Flow<MovieResponse> {
        TODO("Not yet implemented")
    }

    override fun getSimilarTvShows(movieId: String): Flow<MovieResponse> {
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