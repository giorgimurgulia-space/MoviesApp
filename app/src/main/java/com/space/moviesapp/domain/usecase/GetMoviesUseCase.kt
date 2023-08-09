package com.space.moviesapp.domain.usecase

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieItem
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(categoryId: String): Flow<PagingData<MovieItem>> {
        return moviesRepository.getMovies(categoryId)
    }
}