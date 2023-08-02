package com.space.moviesapp.common.maper

import com.space.moviesapp.common.utils.MoviesConstants.IMAGE_BASE_URL
import com.space.moviesapp.data.remote.dto.GenresDto
import com.space.moviesapp.data.remote.dto.MoviesPageDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MoviesPageModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieUIModel

fun MovieCategoryModel.toUIModel() = MovieCategoryUIModel(id, title)

fun MoviesPageDto.toDomainModel(genresMap: HashMap<Int, String>) = MoviesPageModel(
    page, results.map {
        MoviesPageModel.MovieItem(
            it.id,
            it.genreIds.map { id ->
                genresMap[id]!!
            },
            it.title,
            it.voteAverage,
            it.releaseDate.dropLast(6),
            IMAGE_BASE_URL + it.posterPath
        )
    },
    totalPages
)

fun MoviesPageModel.toUIModel() = MovieUIModel(
    page, results.map {
        MovieUIModel.MovieUIItem(
            it.id,
            it.genres,
            it.title,
            it.rating,
            it.releaseDate,
            it.poster
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
