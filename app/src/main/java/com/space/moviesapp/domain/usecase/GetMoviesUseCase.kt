package com.space.moviesapp.domain.usecase

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {
    fun invoke(categoryId: String): Flow<PagingData<MovieItemModel>> {
        return moviesRepository.getMovies(categoryId)
    }
}