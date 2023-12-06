package com.example.movieapp.repository

import com.example.movieapp.network.model.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchData(query: String, url: String): Flow<SearchResponse>
}