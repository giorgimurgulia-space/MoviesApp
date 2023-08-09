package com.space.moviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieItemDto
import java.util.concurrent.CancellationException

class MoviesFavoritesPagingSource(
    private val moviesDao: MoviesDao,
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val currentPage = params.key ?: 0
            val response =
                moviesDao.getFavouriteMovies(params.loadSize, currentPage * params.loadSize)


            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}