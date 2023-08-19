package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.remote.dto.MovieItemDto
import com.space.moviesapp.domain.model.MovieItemModel

class MovieItemDtoToDomainMapper {
    fun invoke(
        model: MovieItemDto,
        genresMap: Map<Int, String>?,
        isFavourite: Boolean
    ): MovieItemModel = with(model) {
        MovieItemModel(
            id ?: 0,
            genresMap?.get(genreIds?.firstOrNull()) ?: "",
            originalTitle ?: "",
            releaseDate?.dropLast(6) ?: "",
            IMAGE_BASE_URL + mainPosterPath,
            IMAGE_BASE_URL + mainPosterPath,
            isFavourite
        )
    }
}