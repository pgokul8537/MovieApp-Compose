package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.network.model.MovieItem
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchList = MutableStateFlow<List<MovieItem>>(emptyList())
    val searchList: StateFlow<List<MovieItem>> = _searchList

    private val _searchResponse = MutableStateFlow<List<MovieItem>>(emptyList())

    val searchResponse = _searchText.debounce(1000L).onEach { _isSearching.update { true } }
        .combine(_searchResponse) { text, searchList ->

        }.onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _searchResponse.value)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getSearchData() {
        viewModelScope.launch {
            movieRepository.getMultiSearchData(searchText.value).catch { exception -> }
                .collect { response ->
                    if (!response.results.isNullOrEmpty()) {
                        _searchResponse.update { response.results }
                        _searchList.update { response.results }
                    }

                }
        }
    }

//    val _searchResponse: StateFlow<DataHandler<MovieResponse>> = _searchResponse

}