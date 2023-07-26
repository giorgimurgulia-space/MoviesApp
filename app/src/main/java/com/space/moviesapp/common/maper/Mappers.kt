package com.space.moviesapp.common.maper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.remote.dto.MoviesDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieUIModel


fun MoviesDto.toDomainModel() = MovieModel(
    page, results.map {
        MovieModel.MovieItem(
            it.id,
            it.title,
            it.voteAverage,
            it.releaseDate.dropLast(6),
            IMAGE_BASE_URL + it.posterPath
        )
    },
    totalPages
)

fun MovieModel.toUIModel() = MovieUIModel(
    page, results.map {
        MovieUIModel.MovieItem(
            it.id,
            it.title,
            it.rating,
            it.releaseDate,
            it.poster
        )
    },
    totalPages
)

fun MovieCategoryModel.toUIModel() = MovieCategoryUIModel(id, title)