package com.space.moviesapp.presentation.ui.details.mapper

import com.space.core.maper.Mapper
import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import java.math.BigDecimal
import java.math.RoundingMode

class MovieDetailsModelToUIMapper :
    com.space.core.maper.Mapper<MovieDetailsModel, MovieDetailsUIModel> {
    override fun invoke(model: MovieDetailsModel): MovieDetailsUIModel = with(model) {
        MovieDetailsUIModel(
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
    }
}