package com.space.moviesapp.data.remote.dto

data class MoviesDto(
    val dates: DatesDto?,
) {
    data class DatesDto(
        val maximum: String?,
        val minimum: String?
    )
}