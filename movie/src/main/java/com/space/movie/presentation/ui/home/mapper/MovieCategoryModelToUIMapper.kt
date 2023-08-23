package com.space.movie.presentation.ui.home.mapper

import com.space.movie.domain.model.MovieCategoryModel
import com.space.movie.presentation.model.MovieCategoryUIModel

class MovieCategoryModelToUIMapper {
    fun invoke(model: MovieCategoryModel, isChecked: Boolean = false): MovieCategoryUIModel =
        MovieCategoryUIModel(model.id, model.urlId, model.title, isChecked)
}