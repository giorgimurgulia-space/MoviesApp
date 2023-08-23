package com.space.movie.presentation.ui.details.mapper

import com.space.movie.data.local.database.entity.MovieEntity
import com.space.movie.presentation.model.MovieDetailsUIModel

class MovieDetailsUIModelToEntity : com.space.core.maper.Mapper<MovieDetailsUIModel, MovieEntity> {
    override fun invoke(model: MovieDetailsUIModel): MovieEntity = with(model) {
        MovieEntity(id, genre, title, releaseDate, backdropPoster, mainPosterPath)
    }
}