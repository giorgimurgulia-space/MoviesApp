package com.space.moviesapp.common.maper

import com.space.moviesapp.common.extensions.formatDoubleToOneDecimal
import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.remote.dto.MoviesDto
import com.space.moviesapp.domain.model.MovieModel


fun MoviesDto.MovieDto.toDomainModel() =
    MovieModel(
        id ?: 0,
        title ?: "",
        voteAverage ?: 0.0.formatDoubleToOneDecimal(),
        releaseDate!!.dropLast(6),
        IMAGE_BASE_URL + posterPath,
    )

