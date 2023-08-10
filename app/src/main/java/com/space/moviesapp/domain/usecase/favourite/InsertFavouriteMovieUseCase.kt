package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository

class InsertFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(movie: MovieItemModel) = favouriteMovieRepository.insertFavoriteMovie(movie)
}