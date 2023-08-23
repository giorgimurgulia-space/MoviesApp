package com.space.movie.presentation.common.mapper

import com.space.movie.data.local.database.entity.MovieEntity
import com.space.movie.presentation.model.MovieItemUIModel

class MovieItemUIModelToEntity : com.space.core.maper.Mapper<MovieItemUIModel, MovieEntity> {
    override fun invoke(model: MovieItemUIModel) =
        MovieEntity(
            model.id,
            model.genre,
            model.title,
            model.releaseDate,
            model.backdropPoster,
            model.mainPosterPath
        )
}