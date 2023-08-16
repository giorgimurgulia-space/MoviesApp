package com.space.moviesapp.presentation.ui.details.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.presentation.model.MovieDetailsUIModel

class MovieDetailsUIModelToEntity : Mapper<MovieDetailsUIModel, MovieEntity> {
    override fun invoke(model: MovieDetailsUIModel): MovieEntity = with(model) {
        MovieEntity(id, genre, title, releaseDate, backdropPoster, mainPosterPath)
    }
}