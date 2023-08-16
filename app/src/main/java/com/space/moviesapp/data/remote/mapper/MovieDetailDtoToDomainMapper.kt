package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.common.utils.MoviesConstants
import com.space.moviesapp.data.remote.dto.MovieDetailsDto
import com.space.moviesapp.domain.model.MovieDetailsModel

class MovieDetailDtoToDomainMapper {
    fun invoke(model: MovieDetailsDto, isFavourite: Boolean): MovieDetailsModel = with(model) {
        MovieDetailsModel(
            id ?: 0,
            genres?.firstOrNull()?.title ?: "",
            originalTitle ?: "",
            overview ?: "",
            if (backdropPosterPath.isNullOrEmpty())
                MoviesConstants.NO_POSTER_IMAGE_URL
            else MoviesConstants.IMAGE_BASE_URL + backdropPosterPath,
            if (mainPosterPath.isNullOrEmpty())
                MoviesConstants.NO_POSTER_IMAGE_URL
            else MoviesConstants.IMAGE_BASE_URL + mainPosterPath,
            releaseDate ?: "",
            runtime ?: 0,
            voteAverage ?: 0.0,
            isFavourite
        )
    }
}