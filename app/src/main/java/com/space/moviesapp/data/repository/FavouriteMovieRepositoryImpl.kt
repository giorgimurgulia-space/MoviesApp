package com.space.moviesapp.data.repository

import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

class FavouriteMovieRepositoryImpl(
    private val moviesDao: MoviesDao
) : FavouriteMovieRepository {

    override suspend fun getFavoriteMovie(): Flow<List<MovieItemModel>> {
        return moviesDao.getFavouriteMovies().map { movies ->
            movies.map {
                it.toDomainModel()
            }
        }
    }

    override suspend fun insertFavoriteMovie(movie: MovieItemModel) {
        moviesDao.insertFavouriteMovie(
            MovieEntity(
                movie.id,
                movie.genres,
                movie.title,
                movie.rating,
                movie.releaseDate,
                movie.poster
            )
        )
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        moviesDao.deleteFavouriteMovie(id)
    }

    override suspend fun isFavoriteMovie(id: Int): Boolean {
        return moviesDao.isFavouriteMovie(id)
    }
}