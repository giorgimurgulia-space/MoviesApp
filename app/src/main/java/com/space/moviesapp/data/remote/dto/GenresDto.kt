package com.space.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenresDto(
    val genres: List<Genres>
) {
    data class Genres(
        val id: Int,
        @SerializedName("name")
        val title: String
    )
}