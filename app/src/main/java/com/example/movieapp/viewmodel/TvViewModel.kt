package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.DataHandler
import com.example.movieapp.network.TvShowDetailsResponse
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.TvShowsResponse
import com.example.movieapp.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(val repository: TvRepository) : ViewModel() {
    private val _trendingMoviesResponse =
        MutableStateFlow<DataHandler<TvShowsResponse>>(DataHandler.Loading)
    val trendingMoviesResponse: StateFlow<DataHandler<TvShowsResponse>> = _trendingMoviesResponse

    private val _popularTvShowsResponse =
        MutableStateFlow<DataHandler<TvShowsResponse>>(DataHandler.Loading)
    val popularTvShowsResponse: StateFlow<DataHandler<TvShowsResponse>> = _popularTvShowsResponse

    private val _airingTodayTvShowsResponse =
        MutableStateFlow<DataHandler<TvShowsResponse>>(DataHandler.Loading)
    val airingTodayTvShowsResponse: StateFlow<DataHandler<TvShowsResponse>> =
        _airingTodayTvShowsResponse

    private val _onTheAirTvShowsResponse =
        MutableStateFlow<DataHandler<TvShowsResponse>>(DataHandler.Loading)
    val onTheAirTvShowsResponse: StateFlow<DataHandler<TvShowsResponse>> = _onTheAirTvShowsResponse

    private val _topRatedTvShows =
        MutableStateFlow<DataHandler<TvShowsResponse>>(DataHandler.Loading)
    val topRatedTvShows: StateFlow<DataHandler<TvShowsResponse>> = _topRatedTvShows

    private val _tvShowDetailsResponse =
        MutableStateFlow<DataHandler<TvShowDetailsResponse>>(DataHandler.Loading)
    val tvShowDetailsResponse: StateFlow<DataHandler<TvShowDetailsResponse>> =
        _tvShowDetailsResponse

    private val _tvCreditsResponse =
        MutableStateFlow<DataHandler<CreditResponse>>(DataHandler.Loading)
    val tvCreditsResponse: StateFlow<DataHandler<CreditResponse>> = _tvCreditsResponse

    private val _similarTvShowsResponse =
        MutableStateFlow<DataHandler<TvShowsResponse>>(DataHandler.Loading)
    val similarTvShowsResponse: StateFlow<DataHandler<TvShowsResponse>> = _similarTvShowsResponse

    private val _tvShowImages =
        MutableStateFlow<DataHandler<MovieImagesResponse>>(DataHandler.Loading)
    val tvShowImages: StateFlow<DataHandler<MovieImagesResponse>> = _tvShowImages

    fun getTrendingMovies() {
        viewModelScope.launch {
            repository.getTrendingTvShows(1).catch { exception ->

            }.collect { movies ->
                _trendingMoviesResponse.value = DataHandler.Success(movies)
            }
        }
    }

    fun getPopularTvShows() {
        viewModelScope.launch {
            repository.getPopularTvShows(1).catch { exception ->

            }.collect { movies ->
                _popularTvShowsResponse.value = DataHandler.Success(movies)
            }
        }
    }

    fun getAiringTodayTvShows() {
        viewModelScope.launch {
            repository.getAiringTodayTvShows(1).catch { exception ->

            }.collect { movies ->
                _airingTodayTvShowsResponse.value = DataHandler.Success(movies)
            }
        }
    }

    fun getOnTheAirTvShows() {
        viewModelScope.launch {
            repository.getOnTheAirTvShows(1).catch { exception ->

            }.collect { movies ->
                _onTheAirTvShowsResponse.value = DataHandler.Success(movies)
            }
        }
    }

    fun getTopRatedTvShows() {
        viewModelScope.launch {
            repository.getTopRatedTvShows(1).catch { exception ->

            }.collect { movies ->
                _topRatedTvShows.value = DataHandler.Success(movies)
            }
        }
    }

    fun getTvShowDetails(movieId: Int) {
        viewModelScope.launch {
            repository.getTvShowDetails(movieId.toString()).catch { exception ->

            }.collect { movies ->
                _tvShowDetailsResponse.value = DataHandler.Success(movies)
            }
        }
    }

    fun getTvShowImages(movieId: Int) {
        viewModelScope.launch {
            repository.getTvShowImages(movieId.toString()).catch { exception ->

            }.collect { movies ->
                _tvShowImages.value = DataHandler.Success(movies)
            }
        }
    }

    fun getSimilarTvShows(movieId: Int) {
        viewModelScope.launch {
            repository.getSimilarTvShows(movieId.toString()).catch { exception ->

            }.collect { movies ->
                _similarTvShowsResponse.value = DataHandler.Success(movies)
            }
        }
    }

    fun getTvCredits(movieId: Int) {
        viewModelScope.launch {
            repository.getTvCredits(movieId.toString()).catch { exception ->

            }.collect { movies ->
                _tvCreditsResponse.value = DataHandler.Success(movies)
            }
        }
    }

}
