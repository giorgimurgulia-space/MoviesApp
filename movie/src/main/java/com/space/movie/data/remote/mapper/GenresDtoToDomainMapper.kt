package com.space.movie.data.remote.mapper

import com.space.movie.data.remote.dto.GenresDto


class GenresDtoToDomainMapper : com.space.core.maper.Mapper<GenresDto, HashMap<Int, String>> {
    override fun invoke(model: GenresDto): HashMap<Int, String> {
        val genresMap = HashMap<Int, String>()
        model.genres.forEach {
            genresMap[it.id] = it.title
        }
        return genresMap
    }
}