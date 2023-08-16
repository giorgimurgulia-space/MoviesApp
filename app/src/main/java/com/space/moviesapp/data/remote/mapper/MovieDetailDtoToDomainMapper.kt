package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.data.remote.dto.MovieDetailsDto
import com.space.moviesapp.domain.model.MovieDetailsModel

class MovieDetailDtoToDomainMapper {
    fun invoke(model: MovieDetailsDto, isFavourite: Boolean): MovieDetailsModel = with(model) {
        MovieDetailsModel(
            id ?: 0,
            genres?.get(0)?.title ?: "",
            originalTitle ?: "",
            overview ?: "",
            posterPath ?: "",
            releaseDate ?: "",
            runtime ?: 0,
            voteAverage ?: 0.0,
            isFavourite
        )
    }
}