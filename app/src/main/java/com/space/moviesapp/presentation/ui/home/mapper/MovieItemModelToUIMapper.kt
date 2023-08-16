package com.space.moviesapp.presentation.ui.home.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieItemModelToUIMapper : Mapper<MovieItemModel, MovieItemUIModel> {
    override fun invoke(model: MovieItemModel): MovieItemUIModel = with(model) {
        MovieItemUIModel(
            id,
            genre,
            title,
            releaseDate,
            backdropPosterPath,
            mainPosterPath,
            isFavourite
        )
    }
}