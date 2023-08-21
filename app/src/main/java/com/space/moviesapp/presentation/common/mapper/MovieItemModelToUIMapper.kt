package com.space.moviesapp.presentation.common.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieItemModelToUIMapper {
    fun invoke(model: MovieItemModel, isFavourite: Boolean): MovieItemUIModel =
        with(model) {
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