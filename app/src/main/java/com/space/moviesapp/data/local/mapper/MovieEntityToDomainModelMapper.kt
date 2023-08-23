package com.space.moviesapp.data.local.mapper

import com.space.core.maper.Mapper
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.domain.model.MovieItemModel

class MovieEntityToDomainModelMapper : com.space.core.maper.Mapper<MovieEntity, MovieItemModel> {
    override fun invoke(model: MovieEntity): MovieItemModel = MovieItemModel(
        model.id,
        model.genre,
        model.title,
        model.releaseDate,
        model.backdropPosterPath,
        model.mainPosterPath
    )
}