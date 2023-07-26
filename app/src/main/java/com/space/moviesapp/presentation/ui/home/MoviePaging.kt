package com.space.moviesapp.presentation.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.moviesapp.domain.usecase.GetPopularMoviesUseCase
import com.space.moviesapp.presentation.model.MovieUIModel
import java.lang.IllegalStateException


class MoviePaging(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : PagingSource<Int, MovieUIModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieUIModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUIModel> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = getPopularMoviesUseCase

            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null

            if (response.page < response.total_pages) {
                nextPageNumber = response.page + 1
            }

            if (response.page > 1) {
                prevPageNumber = response.page - 1
            }

            LoadResult.Page(
                data = response.data,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}