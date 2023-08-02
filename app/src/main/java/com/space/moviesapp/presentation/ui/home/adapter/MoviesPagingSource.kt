package com.space.moviesapp.presentation.ui.home.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieItemDto

class MoviesPagingSource(
    private val apiService: ApiService,
    private val categoryId: String,
) : PagingSource<Int, MovieItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getMoviesPage(categoryId, currentPage)
            val responseBody = response.body()!!

            val totalPages = responseBody.totalPages

            LoadResult.Page(
                data = responseBody.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == totalPages) null else currentPage + 1
            )
        } catch (e: Exception) {
            throw ApiError(Throwable())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemDto>): Int? {
        return state.anchorPosition
    }
}