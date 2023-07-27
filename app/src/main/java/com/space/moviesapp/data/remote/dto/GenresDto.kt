package com.space.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenresDto(
    val genres: List<GenreItem>
) {
    data class GenreItem(
        val id: Int,
        @SerializedName("name")
        val title: String
    )
}