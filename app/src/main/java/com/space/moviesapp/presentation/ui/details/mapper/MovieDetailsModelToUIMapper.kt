package com.space.moviesapp.presentation.ui.details.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import java.math.BigDecimal
import java.math.RoundingMode

class MovieDetailsModelToUIMapper : Mapper<MovieDetailsModel, MovieDetailsUIModel> {
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