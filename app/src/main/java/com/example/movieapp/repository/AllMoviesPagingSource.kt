package com.example.movieapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.network.ApiInterface
import com.example.movieapp.network.model.MovieItem

class AllMoviesPagingSource constructor(private val apiInterface: ApiInterface) :
    PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val pageNo = params.key ?: 1
        try {
            val response = apiInterface.getPopularMovies(pageNo)
            response.results.let {
                return LoadResult.Page(
                    data = response.results!!,
                    prevKey = if (pageNo == 1) null else pageNo.minus(1),
                    nextKey = if (response.results.isEmpty()) null else pageNo.plus(1)
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}