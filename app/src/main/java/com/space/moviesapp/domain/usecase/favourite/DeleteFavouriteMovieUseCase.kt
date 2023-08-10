package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.domain.repository.FavouriteMovieRepository

class DeleteFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(id: Int) = favouriteMovieRepository.deleteFavoriteMovie(id)
}