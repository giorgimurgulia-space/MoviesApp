package com.space.movie.data.repository

import com.space.movie.data.local.database.dao.MoviesDao
import com.space.movie.data.remote.api.ApiService
import com.space.movie.data.remote.mapper.MovieDetailDtoToDomainMapper
import com.space.movie.domain.model.MovieDetailsModel
import com.space.movie.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRepositoryImpl(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val movieDetailDtoToDomainMapper: MovieDetailDtoToDomainMapper
) : MovieDetailsRepository {
    override fun invoke(movieId: Int): Flow<MovieDetailsModel> = flow {
        val response = apiService.getMovie(movieId)
        if (response.isSuccessful) {
            emit(
                movieDetailDtoToDomainMapper.invoke(
                    response.body()!!,
                    moviesDao.isFavouriteMovie(movieId)
                )
            )
        } else
            throw com.space.core.resource.ApiError(Throwable())
    }
}