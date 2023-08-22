package com.space.moviesapp.presentation.ui.home.mapper

import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel

class MovieCategoryModelToUIMapper {
    fun invoke(model: MovieCategoryModel, isChecked: Boolean = false): MovieCategoryUIModel =
        MovieCategoryUIModel(model.id, model.urlId, model.title, isChecked)
}