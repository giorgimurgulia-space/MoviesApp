package com.space.moviesapp.data.repository

import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.mapper.MovieDetailDtoToDomainMapper
import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRepositoryImpl(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val movieDetailDtoToDomainMapper: MovieDetailDtoToDomainMapper
) : MovieDetailsRepository {
    override suspend fun invoke(movieId: Int): Flow<MovieDetailsModel> = flow {
        val response = apiService.getMovie(movieId)
        if (response.isSuccessful) {
            emit(
                movieDetailDtoToDomainMapper.invoke(
                    response.body()!!,
                    moviesDao.isFavouriteMovie(response.body()!!.id ?: 0)
                )
            )
        } else
            throw ApiError(Throwable())
    }
}