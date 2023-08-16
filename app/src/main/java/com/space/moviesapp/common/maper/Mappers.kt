package com.space.moviesapp.common.maper

import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import com.space.moviesapp.presentation.model.MovieItemUIModel
import java.math.BigDecimal
import java.math.RoundingMode

fun MovieDetailsModel.toUIModel() = MovieDetailsUIModel(
    id,
    genre,
    title,
    overview,
    backdropPoster,
    mainPoster,
    releaseDate.dropLast(6),
    runtime,
    BigDecimal(voteAverage).setScale(2, RoundingMode.FLOOR).toDouble(),
    isFavourite
)

fun MovieDetailsUIModel.toEntity() =
    MovieEntity(id, genre, title, releaseDate, backdropPoster, mainPosterPath)
