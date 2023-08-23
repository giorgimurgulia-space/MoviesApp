package com.space.moviesapp.data.remote.mapper

import com.space.core.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.remote.dto.MovieDetailsDto
import com.space.moviesapp.domain.model.MovieDetailsModel

class MovieDetailDtoToDomainMapper {
    fun invoke(model: MovieDetailsDto, isFavourite: Boolean): MovieDetailsModel = with(model) {
        MovieDetailsModel(
            id ?: 0,
            genres?.firstOrNull()?.title ?: "",
            originalTitle ?: "",
            overview ?: "",
            IMAGE_BASE_URL + backdropPosterPath,
            IMAGE_BASE_URL + mainPosterPath,
            releaseDate ?: "",
            runtime ?: 0,
            voteAverage ?: 0.0,
            isFavourite
        )
    }
}