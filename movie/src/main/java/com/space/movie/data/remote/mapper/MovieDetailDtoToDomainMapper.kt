package com.space.movie.data.remote.mapper

import com.space.core.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.movie.data.remote.dto.MovieDetailsDto
import com.space.movie.domain.model.MovieDetailsModel

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