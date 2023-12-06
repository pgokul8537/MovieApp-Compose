package com.example.movieapp.repository

import com.example.movieapp.network.ApiInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val apiInterface: ApiInterface) : SearchRepository {

    override fun getSearchData(query: String, url: String) = flow {
        emit(
            apiInterface.getMultiSearchData(
                url = url,
                pageNo = 1,
                query = query
            )
        )
    }
}