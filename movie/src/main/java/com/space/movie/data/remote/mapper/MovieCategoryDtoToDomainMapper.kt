package com.space.movie.data.remote.mapper

import com.space.movie.data.remote.dto.MovieCategoryDto
import com.space.movie.domain.model.MovieCategoryModel


class MovieCategoryDtoToDomainMapper {
    fun invoke(model: MovieCategoryDto, id: Int): MovieCategoryModel =
        MovieCategoryModel(id, model.urlId, model.title)
}