package com.space.moviesapp.presentation.ui.home.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel

class MovieCategoryModelToUIMapper : Mapper<MovieCategoryModel, MovieCategoryUIModel> {
    override fun invoke(model: MovieCategoryModel): MovieCategoryUIModel =
        MovieCategoryUIModel(model.id, model.urlId, model.title)
}