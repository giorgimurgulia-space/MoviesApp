package com.space.moviesapp.domain.usecase.favourite

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(limit: Int, offset: Int): Flow<PagingData<MovieItemModel>> {
        return favouriteMovieRepository.getFavoriteMovie(limit, offset)
    }
}