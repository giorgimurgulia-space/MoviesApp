package com.space.movie.presentation.common.mapper

import com.space.movie.domain.model.MovieItemModel
import com.space.movie.presentation.model.MovieItemUIModel

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