package com.space.movie.data.remote.mapper

import com.space.core.utils.MoviesConstants
import com.space.movie.data.remote.dto.MoviesPageDto
import com.space.movie.domain.model.MovieItemModel
import com.space.movie.domain.model.MoviesPageModel

class MoviePageDtoToDomainMapper {
    fun invoke(
        model: MoviesPageDto,
        genresMap: Map<Int, String>?,
    ): MoviesPageModel = with(model) {
        MoviesPageModel(page, results.map {
            MovieItemModel(
                it.id ?: 0,
                genresMap?.get(it.genreIds?.firstOrNull()) ?: "",
                it.originalTitle ?: "",
                it.releaseDate?.dropLast(6) ?: "",
                MoviesConstants.IMAGE_BASE_URL + it.mainPosterPath,
                MoviesConstants.IMAGE_BASE_URL + it.mainPosterPath
            )
        }, totalPages)
    }
}