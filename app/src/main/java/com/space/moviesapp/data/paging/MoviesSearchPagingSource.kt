package com.space.moviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieItemDto
import java.util.concurrent.CancellationException

class MoviesSearchPagingSource(
    private val apiService: ApiService,
    private val query: String,
) : PagingSource<Int, MovieItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.searchMovies(query, currentPage)
            val responseBody = response.body()!!

            val totalPages = responseBody.totalPages

            LoadResult.Page(
                data = responseBody.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == totalPages) null else currentPage + 1
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            throw ApiError(Throwable())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemDto>): Int? {
        return state.anchorPosition
    }
}