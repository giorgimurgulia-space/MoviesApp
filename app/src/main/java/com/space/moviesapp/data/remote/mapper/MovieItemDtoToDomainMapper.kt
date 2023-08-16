package com.space.moviesapp.data.remote.mapper

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
            genresMap[genreIds?.get(0)] ?: "",
            originalTitle ?: "",
            releaseDate?.dropLast(6) ?: "",
            posterPath ?: "",
            isFavourite
        )
    }
}