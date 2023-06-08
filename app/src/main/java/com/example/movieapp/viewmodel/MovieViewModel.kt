package com.example.movieapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.DataHandler
import com.example.movieapp.network.model.MovieResponse
import com.example.movieapp.repository.MovieRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    fun getPopularMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getPopularMovies(pageNo = pageNo)
                .catch { exception ->

                }.collect { movies ->
                    delay(3000)
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
}