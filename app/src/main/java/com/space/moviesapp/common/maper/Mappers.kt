package com.space.moviesapp.common.maper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.remote.dto.*
import com.space.moviesapp.domain.model.*
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import com.space.moviesapp.presentation.model.MovieItemUIModel
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.collections.HashMap

fun MovieCategoryModel.toUIModel() = MovieCategoryUIModel(id, urlId, title)

fun MovieItemModel.toUIModel() =
    MovieItemUIModel(id, listOf(genre), title, releaseDate, posterPath, isFavourite)

fun MovieDetailsModel.toUIModel() = MovieDetailsUIModel(
    id,
    listOf(genres),
    originalTitle,
    overview,
    posterPath,
    releaseDate.dropLast(6),
    runtime,
    BigDecimal(voteAverage).setScale(2, RoundingMode.FLOOR).toDouble(),
    isFavourite
)

fun MovieItemUIModel.toEntity() =
    MovieEntity(id, genres.firstOrNull() ?: "", title, releaseDate, poster)

fun MovieItemModel.toEntity() =
    MovieEntity(id, listOf(genre).firstOrNull() ?: "", title, releaseDate, posterPath)

fun MovieEntity.toDomainModel() =
    MovieItemModel(id, genres, title, releaseDate,poster, true)

fun MovieDetailsUIModel.toEntity() =
    MovieEntity(id!!, genres.first(), originalTitle!!, releaseDate!!, posterPath!!)
