package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
class ChangeMovieFavouriteStatusUseCase(
    private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(movie: MovieItemModel) {
        if (favouriteMovieRepository.isFavoriteMovie(movie.id)) {
            favouriteMovieRepository.deleteFavoriteMovie(movie.id)
        } else {
            favouriteMovieRepository.insertFavoriteMovie(movie)
        }
    }
}