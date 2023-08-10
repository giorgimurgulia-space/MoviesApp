package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.domain.model.MovieCategoryModel

class MovieCategoryDtoToDomainMapper(private val id: Int) :
    Mapper<MovieCategoryDto, MovieCategoryModel> {
    override fun invoke(model: MovieCategoryDto): MovieCategoryModel =
        MovieCategoryModel(id, model.urlId, model.title)

}