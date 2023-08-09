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

fun MoviesPageDto.toDomainModel(genresMap: Map<Int, String>) = MoviesPageModel(
    page, results.map { it.toDomainModel(genresMap) }, totalPages
)

fun MovieItemDto.toDomainModel(genresMap: Map<Int, String>) =
    MovieItem(
        id, genreIds.map { id -> genresMap[id] }, title, voteAverage, releaseDate.dropLast(6),
        IMAGE_BASE_URL + posterPath
    )

fun MoviesPageModel.toUIModel() = MoviePageUIModel(
    page, results.map { it.toUIModel() }, totalPages
)

fun MovieItem.toUIModel() = MovieItemUIModel(id, genres, title, rating, releaseDate, poster)

fun GenresDto.toDomainModel(): HashMap<Int, String> {
    val genresMap = HashMap<Int, String>()
    this.genres.forEach {
        genresMap[it.id] = it.title
    }
    return genresMap
}

fun MovieDetailsDto.toDomainModel() = MovieDetailsModel(
    id,
    genres.map { it.title },
    originalTitle,
    overview,
    IMAGE_BASE_URL + posterPath,
    releaseDate,
    runtime,
    voteAverage
)

fun MovieDetailsModel.toUIModel() = MovieDetailsUIModel(
    id,
    genres,
    originalTitle,
    overview,
    posterPath,
    releaseDate.dropLast(6),
    runtime,
    BigDecimal(voteAverage).setScale(2, RoundingMode.FLOOR).toDouble()
)
fun MovieEntity.toDomainModel() = MovieItemModel(id, genres, title, rating, releaseDate, poster)