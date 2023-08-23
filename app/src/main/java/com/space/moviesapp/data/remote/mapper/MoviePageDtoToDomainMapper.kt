package com.space.moviesapp.data.remote.mapper

import com.space.core.utils.MoviesConstants
import com.space.moviesapp.data.remote.dto.MoviesPageDto
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.model.MoviesPageModel

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
                com.space.core.utils.MoviesConstants.IMAGE_BASE_URL + it.mainPosterPath,
                com.space.core.utils.MoviesConstants.IMAGE_BASE_URL + it.mainPosterPath
            )
        }, totalPages)
    }
}