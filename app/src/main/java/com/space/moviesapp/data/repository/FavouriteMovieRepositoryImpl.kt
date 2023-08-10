package com.space.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.paging.MoviesFavoritesPagingSource
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteMovieRepositoryImpl(
    private val moviesDao: MoviesDao
) : FavouriteMovieRepository {

    override fun getFavoriteMovie(): Flow<PagingData<MovieItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesFavoritesPagingSource(moviesDao) }
        ).flow.map {
            it.map { movie ->
                movie.toDomainModel()
            }
        }
    }

    override suspend fun insertFavoriteMovie(movie: MovieEntity) {
        moviesDao.insertFavouriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        moviesDao.deleteFavouriteMovie(id)
    }

    override suspend fun isFavoriteMovie(id: Int): Boolean {
        return moviesDao.isFavouriteMovie(id)
    }
}