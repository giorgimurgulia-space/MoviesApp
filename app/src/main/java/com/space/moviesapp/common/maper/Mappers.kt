package com.space.moviesapp.common.maper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.remote.dto.GenresDto
import com.space.moviesapp.data.remote.dto.MovieItemDto
import com.space.moviesapp.data.remote.dto.MoviesPageDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItem
import com.space.moviesapp.domain.model.MoviesPageModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieUIItem
import com.space.moviesapp.presentation.model.MovieUIModel

fun MovieCategoryModel.toUIModel() = MovieCategoryUIModel(id, title)

fun MoviesPageDto.toDomainModel(genresMap: Map<Int, String>) = MoviesPageModel(
    page, results.map { it.toDomainModel(genresMap) }, totalPages
)

fun MovieItemDto.toDomainModel(genresMap: Map<Int, String>) =
    MovieItem(
        id, genreIds.map { id -> genresMap[id] }, title, voteAverage, releaseDate.dropLast(6),
        IMAGE_BASE_URL + posterPath
    )

fun MoviesPageModel.toUIModel() = MovieUIModel(
    page, results.map { it.toUIModel() }, totalPages
)

fun MovieItem.toUIModel() = MovieUIItem(id, genres, title, rating, releaseDate, poster)

fun GenresDto.toDomainModel(): HashMap<Int, String> {
    val genresMap = HashMap<Int, String>()
    this.genres.forEach {
        genresMap[it.id] = it.title
    }
    return genresMap
}
