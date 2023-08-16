package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.common.utils.MoviesConstants.NO_POSTER_IMAGE_URL
import com.space.moviesapp.data.remote.dto.MovieItemDto
import com.space.moviesapp.domain.model.MovieItemModel

class MovieItemDtoToDomainMapper {
    fun invoke(
        model: MovieItemDto,
        genresMap: Map<Int, String>,
        isFavourite: Boolean
    ): MovieItemModel = with(model) {
        MovieItemModel(
            id ?: 0,
            genresMap[genreIds?.firstOrNull()] ?: "",
            originalTitle ?: "",
            releaseDate?.dropLast(6) ?: "",
            if (posterPath.isNullOrEmpty())
                NO_POSTER_IMAGE_URL
            else IMAGE_BASE_URL + posterPath,
            isFavourite
        )
    }
}