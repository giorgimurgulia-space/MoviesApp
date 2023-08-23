package com.space.movie.data.local.mapper

import com.space.movie.data.local.database.entity.MovieEntity
import com.space.movie.domain.model.MovieItemModel

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