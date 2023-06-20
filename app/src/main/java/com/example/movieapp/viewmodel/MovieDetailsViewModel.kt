package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel
@Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _moviesDetailsResponse =
        MutableStateFlow<DataHandler<MovieDetailsResponse>>(DataHandler.Loading)
    val moviesDetailsResponse: StateFlow<DataHandler<MovieDetailsResponse>> = _moviesDetailsResponse

    fun getMovieDetails(movieId: Int) {
        if (_moviesDetailsResponse.value == DataHandler.Loading) {
            viewModelScope.launch {
                movieRepository.getMoviesDetails(movieId.toString())
                    .catch { exception ->

                    }.collect { movies ->
                        _moviesDetailsResponse.value = DataHandler.Success(movies)
                    }
            }
        }
    }

    private val _movieImagesResponse =
        MutableStateFlow<DataHandler<MovieImagesResponse>>(DataHandler.Loading)
    val movieImagesResponse: StateFlow<DataHandler<MovieImagesResponse>> = _movieImagesResponse


    fun getMoviesImages(movieId: Int) {
        if (_movieImagesResponse.value == DataHandler.Loading) {
            viewModelScope.launch {
                movieRepository.getMoviesImages(movieId.toString())
                    .catch { exception ->

                    }.collect { movies ->
                        _movieImagesResponse.value = DataHandler.Success(movies)
                    }
            }
        }
    }

    private val _similarMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val similarMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _similarMoviesResponse

    fun getSimilarMovies(movieId: Int) {
        if (similarMoviesResponse.value == DataHandler.Loading) {
            viewModelScope.launch {
                movieRepository.getSimilarMovies(movieId.toString())
                    .catch { exception ->

                    }.collect { movies ->
                        _similarMoviesResponse.value = DataHandler.Success(movies)
                    }
            }
        }
    }

    private val _creditResponse =
        MutableStateFlow<DataHandler<CreditResponse>>(DataHandler.Loading)
    val creditResponse: StateFlow<DataHandler<CreditResponse>> = _creditResponse

    fun getMovieCredits(movieId: Int) {
        if (_creditResponse.value == DataHandler.Loading) {
            viewModelScope.launch {
                movieRepository.getMovieCredits(movieId.toString())
                    .catch { exception ->

                    }.collect { movies ->
                        _creditResponse.value = DataHandler.Success(movies)
                    }
            }
        }
    }

}