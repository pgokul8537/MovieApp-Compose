package com.example.movieapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.repository.MovieRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var popularMoviesResponse: MutableState<DataHandler<MovieResponse>> =
        mutableStateOf(DataHandler.Loading)
    var nowPlayingMoviesResponse: MutableState<DataHandler<MovieResponse>> =
        mutableStateOf(DataHandler.Loading)
    var upcomingMoviesResponse: MutableState<DataHandler<MovieResponse>> =
        mutableStateOf(DataHandler.Loading)

    var topRatedMoviesResponse: MutableState<DataHandler<MovieResponse>> =
        mutableStateOf(DataHandler.Loading)
    var similarMoviesResponse: MutableState<DataHandler<MovieResponse>> =
        mutableStateOf(DataHandler.Loading)
    private val _trendingMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val trendingMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _trendingMoviesResponse

    private val _moviesDetailsResponse =
        MutableStateFlow<DataHandler<MovieDetailsResponse>>(DataHandler.Loading)
    val moviesDetailsResponse: StateFlow<DataHandler<MovieDetailsResponse>> = _moviesDetailsResponse

    private val _movieImagesResponse =
        MutableStateFlow<DataHandler<MovieImagesResponse>>(DataHandler.Loading)
    val movieImagesResponse: StateFlow<DataHandler<MovieImagesResponse>> = _movieImagesResponse
    private val _creditResponse =
        MutableStateFlow<DataHandler<CreditResponse>>(DataHandler.Loading)
    val creditResponse: StateFlow<DataHandler<CreditResponse>> = _creditResponse

    private val _personDetailResponse =
        MutableStateFlow<DataHandler<PersonDetailResponse>>(DataHandler.Loading)
    val personDetailResponse: StateFlow<DataHandler<PersonDetailResponse>> = _personDetailResponse

    fun getPopularMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getPopularMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    popularMoviesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getNowPlayingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    nowPlayingMoviesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getUpcomingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    upcomingMoviesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getTrendingMovies() {
        viewModelScope.launch {
            movieRepository.getTrendingMovies()
                .catch { exception ->

                }.collect { movies ->
                    _trendingMoviesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getTopRatedMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getTopRatedMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    topRatedMoviesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMoviesDetails(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _moviesDetailsResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getMoviesImages(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMoviesImages(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _movieImagesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getPersonImages(personId: Int) {
        viewModelScope.launch {
            movieRepository.getMoviesImages(personId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _movieImagesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getPersonDetails(personId: Int) {
        viewModelScope.launch {
            movieRepository.getPersonDetails(personId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _personDetailResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieCredits(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _creditResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getSimilarMovies(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    similarMoviesResponse.value = DataHandler.Success(movies)
                    Log.e("movies", Gson().toJson(movies))
                }
        }
    }

    fun getAllMovies(url: String) = movieRepository.getAllMovies(url).cachedIn(viewModelScope)
}