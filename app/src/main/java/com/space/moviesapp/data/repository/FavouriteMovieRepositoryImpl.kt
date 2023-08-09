package com.space.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.common.maper.toEntity
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.paging.MoviesFavoritesPagingSource
import com.space.moviesapp.data.paging.MoviesPagingSource
import com.space.moviesapp.data.paging.MoviesSearchPagingSource
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.*

class FavouriteMovieRepositoryImpl(
    private val moviesDao: MoviesDao
) : FavouriteMovieRepository {

    override suspend fun getFavoriteMovie(
        limit: Int,
        offset: Int
    ): Flow<PagingData<MovieItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesFavoritesPagingSource(moviesDao) }
        ).flow.map {
            it.map { movie ->
                movie.toDomainModel()
            }
        }
    }

    override suspend fun insertFavoriteMovie(movie: MovieItemModel) {
        moviesDao.insertFavouriteMovie(movie.toEntity())
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        moviesDao.deleteFavouriteMovie(id)
    }

    override suspend fun isFavoriteMovie(id: Int): Boolean {
        return moviesDao.isFavouriteMovie(id)
    }
}