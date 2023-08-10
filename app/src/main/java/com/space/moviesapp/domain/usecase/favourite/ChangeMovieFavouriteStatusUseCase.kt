package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.domain.repository.FavouriteMovieRepository

class ChangeMovieFavouriteStatusUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(movie: MovieEntity) {
        if (favouriteMovieRepository.isFavoriteMovie(movie.id)) {
            favouriteMovieRepository.deleteFavoriteMovie(movie.id)
        } else {
            favouriteMovieRepository.insertFavoriteMovie(movie)
        }
    }
}