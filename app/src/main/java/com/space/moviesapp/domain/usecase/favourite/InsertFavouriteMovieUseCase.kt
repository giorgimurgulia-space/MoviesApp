package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.domain.repository.FavouriteMovieRepository

class InsertFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(movie: MovieEntity) = favouriteMovieRepository.insertFavoriteMovie(movie)
}