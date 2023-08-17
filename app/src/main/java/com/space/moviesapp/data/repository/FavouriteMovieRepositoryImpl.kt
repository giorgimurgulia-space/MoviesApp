package com.space.moviesapp.data.repository

import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.local.mapper.MovieEntityToDomainModelMapper
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteMovieRepositoryImpl(
    private val moviesDao: MoviesDao,
    private val movieEntityToDomainModelMapper: MovieEntityToDomainModelMapper
) : FavouriteMovieRepository {

    override fun getFavoriteMovie(): Flow<List<MovieItemModel>> {
        return moviesDao.getFavouriteMovies().map {
            it.map { item ->
                movieEntityToDomainModelMapper(item)
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