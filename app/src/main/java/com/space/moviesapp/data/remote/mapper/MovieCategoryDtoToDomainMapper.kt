package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.domain.model.MovieCategoryModel

class MovieCategoryDtoToDomainMapper {
    fun invoke(model: MovieCategoryDto, id: Int): MovieCategoryModel =
        MovieCategoryModel(id, model.urlId, model.title)
}