package com.space.moviesapp.common.maper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.remote.dto.*
import com.space.moviesapp.domain.model.*
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import com.space.moviesapp.presentation.model.MovieItemUIModel
import com.space.moviesapp.presentation.model.MoviePageUIModel
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.collections.HashMap

fun MovieCategoryDto.toDomainModel(id: Int) = MovieCategoryModel(id, urlId, title)
fun MovieCategoryModel.toUIModel() = MovieCategoryUIModel(id, urlId, title)

fun MovieItemDto.toDomainModel(genresMap: Map<Int, String>, isFavourite: Boolean) =
    MovieItemModel(
        id, genreIds.map { id -> genresMap[id] }, title, releaseDate.dropLast(6),
        IMAGE_BASE_URL + posterPath, isFavourite
    )

fun MovieItemModel.toUIModel() =
    MovieItemUIModel(id, genres, title, releaseDate, poster, isFavourite)

fun GenresDto.toDomainModel(): HashMap<Int, String> {
    val genresMap = HashMap<Int, String>()
    this.genres.forEach {
        genresMap[it.id] = it.title
    }
    return genresMap
}

fun MovieDetailsDto.toDomainModel(isFavourite: Boolean) = MovieDetailsModel(
    id,
    genres.map { it.title },
    originalTitle,
    overview,
    IMAGE_BASE_URL + posterPath,
    releaseDate,
    runtime,
    voteAverage,
    isFavourite
)

fun MovieDetailsModel.toUIModel() = MovieDetailsUIModel(
    id,
    genres,
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
    MovieEntity(id, genres.firstOrNull() ?: "", title, releaseDate, poster)

fun MovieEntity.toDomainModel() =
    MovieItemModel(id, listOf(genres), title, poster, releaseDate, true)

fun MovieDetailsUIModel.toEntity() =
    MovieEntity(id!!, genres.first(), originalTitle!!, releaseDate!!, posterPath!!)
