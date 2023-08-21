package com.space.moviesapp.presentation.ui.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import java.util.concurrent.CancellationException

class MoviesPagingSource(
    private val categoryId: String,
    private val getMoviesUseCase: GetMoviesUseCase
) : PagingSource<Int, MovieItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemModel> {
        return try {
            val currentPage = params.key ?: 1

            val response = getMoviesUseCase.invoke(categoryId, currentPage)
            val totalPages = response.totalPages

            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == totalPages) null else currentPage + 1
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemModel>): Int? {
        return state.anchorPosition
    }
}