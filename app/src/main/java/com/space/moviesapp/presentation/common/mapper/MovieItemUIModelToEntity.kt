package com.space.moviesapp.presentation.common.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieItemUIModelToEntity : Mapper<MovieItemUIModel, MovieEntity> {
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