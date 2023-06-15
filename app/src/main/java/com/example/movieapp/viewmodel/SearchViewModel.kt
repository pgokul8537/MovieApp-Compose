package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.network.model.SearchResponse
import com.example.movieapp.repository.SearchRepository
import com.example.movieapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchList = MutableStateFlow<List<SearchResponse.SearchItem>>(emptyList())
    val searchList: StateFlow<List<SearchResponse.SearchItem>> = _searchList

    /*private val _searchResponse = MutableStateFlow<List<SearchResponse.SearchItem>>(emptyList())

    val searchResponse = _searchText.debounce(1000L).onEach { _isSearching.update { true } }
        .combine(_searchResponse) { text, searchList ->

        }.onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _searchResponse.value)
*/
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getSearchData() {
        viewModelScope.launch {
            repository.getSearchData(searchText.value, Constants.URL_SEARCH_MULTI)
                .catch { exception -> }
                .collect { response ->
                    if (!response.results.isNullOrEmpty()) {
                        _searchList.update { response.results }
                    }
                }
        }
    }

    fun clearText() {
        _searchText.update { "" }
    }
}

enum class MediaType(var value: String) {
    MOVIE("movie"),
    TV("tv"),
    PERSON("person")
}