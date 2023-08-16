package com.space.moviesapp.data.remote.mapper

import com.space.moviesapp.common.maper.Mapper
import com.space.moviesapp.data.remote.dto.GenresDto

class GenresDtoToDomainMapper : Mapper<GenresDto, HashMap<Int, String>> {
    override fun invoke(model: GenresDto): HashMap<Int, String> {
        val genresMap = HashMap<Int, String>()
        model.genres.forEach {
            genresMap[it.id] = it.title
        }
        return genresMap
    }
}