package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.CreditResponse
import com.example.movieapp.network.model.MovieDetailsResponse
import com.example.movieapp.network.model.MovieImagesResponse
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.network.model.PersonDetailResponse
import com.example.movieapp.network.model.PersonImagesResponse
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _popularMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val popularMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _popularMoviesResponse
    private val _nowPlayingMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val nowPlayingMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _nowPlayingMoviesResponse
    private val _upcomingMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val upcomingMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _upcomingMoviesResponse
    private val _topRatedMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val topRatedMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _topRatedMoviesResponse
    private val _similarMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val similarMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _similarMoviesResponse
    private val _personMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val personMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _personMoviesResponse
    private val _trendingMoviesResponse =
        MutableStateFlow<DataHandler<MovieResponse>>(DataHandler.Loading)
    val trendingMoviesResponse: StateFlow<DataHandler<MovieResponse>> = _trendingMoviesResponse
    private val _moviesDetailsResponse =
        MutableStateFlow<DataHandler<MovieDetailsResponse>>(DataHandler.Loading)
    val moviesDetailsResponse: StateFlow<DataHandler<MovieDetailsResponse>> = _moviesDetailsResponse

    private val _movieImagesResponse =
        MutableStateFlow<DataHandler<MovieImagesResponse>>(DataHandler.Loading)
    val movieImagesResponse: StateFlow<DataHandler<MovieImagesResponse>> = _movieImagesResponse

    private val _personImagesResponse =
        MutableStateFlow<DataHandler<PersonImagesResponse>>(DataHandler.Loading)
    val personImagesResponse: StateFlow<DataHandler<PersonImagesResponse>> = _personImagesResponse
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
                    _popularMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getNowPlayingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    _nowPlayingMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getUpcomingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    _upcomingMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getTrendingMovies() {
        viewModelScope.launch {
            movieRepository.getTrendingMovies()
                .catch { exception ->

                }.collect { movies ->
                    _trendingMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getTopRatedMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getTopRatedMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    _topRatedMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMoviesDetails(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _moviesDetailsResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getMoviesImages(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMoviesImages(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _movieImagesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getPersonImages(personId: Int) {
        viewModelScope.launch {
            movieRepository.getPersonImages(personId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _personImagesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getPersonDetails(personId: Int) {
        viewModelScope.launch {
            movieRepository.getPersonDetails(personId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _personDetailResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieCredits(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _creditResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getSimilarMovies(movieId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _similarMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getPersonMovies(personId: Int) {
        viewModelScope.launch {
            movieRepository.getPersonMovies(personId.toString())
                .catch { exception ->

                }.collect { movies ->
                    _personMoviesResponse.value = DataHandler.Success(movies)
                }
        }
    }

    fun getAllMovies(url: String) = movieRepository.getAllMovies(url).cachedIn(viewModelScope)
}