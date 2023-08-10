package com.space.moviesapp.data.repository

import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRepositoryImpl(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao
) : MovieDetailsRepository {
    override suspend fun invoke(movieId: Int): Flow<MovieDetailsModel> = flow {
        val response = apiService.getMovie(movieId)
        if (response.isSuccessful) {
            emit(
                response.body()!!.toDomainModel(
                    moviesDao.isFavouriteMovie(response.body()!!.id)
                )
            )
        } else
            throw ApiError(Throwable())
    }
}