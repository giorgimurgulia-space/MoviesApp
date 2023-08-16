package com.space.moviesapp.presentation.ui.home.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieItemUIModelToEntity : Mapper<MovieItemUIModel, MovieEntity> {
    override fun invoke(model: MovieItemUIModel): MovieEntity = with(model) {
        MovieEntity(id, genre, title, releaseDate, backdropPoster, mainPosterPath)
    }
}