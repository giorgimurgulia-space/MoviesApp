package com.space.moviesapp.common.maper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.data.remote.dto.GenresDto
import com.space.moviesapp.data.remote.dto.MoviesDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.model.MovieModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieItemUIModel
import com.space.moviesapp.presentation.model.MovieUIModel


//todo mapper class
fun MovieCategoryModel.toUIModel() = MovieCategoryUIModel(id, title)

fun MoviesDto.toDomainModel(genresMap: HashMap<Int, String>) = MovieModel(
    page, results.map {
        MovieItemModel(
            it.id,
            genresMap[it.genreIds.first()] ?: "",
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
        MovieItemUIModel(
            it.id,
            it.genres,
            it.title,
            it.rating,
            it.releaseDate,
            it.poster,
            it.isFavorite
        )
    },
    totalPages
)

fun GenresDto.toDomainModel(): HashMap<Int, String> {
    val genresMap = HashMap<Int, String>()
    this.genres.forEach {
        genresMap[it.id] = it.title
    }
    return genresMap
}

fun MovieEntity.toDomainModel() = MovieItemModel(id, genres, title, rating, releaseDate, poster)
