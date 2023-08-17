package com.space.moviesapp.presentation.common.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.hide
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.common.extensions.show
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieViewHolder(
    private val binding: LayoutMovieItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movie: MovieItemUIModel,
        favouriteMoviesIds: MutableMap<Int, Boolean> = mutableMapOf()
    ) = with(binding) {
        if (favouriteMoviesIds[movie.id] != null) {
            if (favouriteMoviesIds[movie.id] != movie.isFavourite) {
                favoriteCheckBox.isChecked = !movie.isFavourite
            } else {
                favoriteCheckBox.isChecked = movie.isFavourite
            }
        } else {
            favoriteCheckBox.isChecked = movie.isFavourite
        }

        if (movie.genre.isNotEmpty()) {
            genresText.show()
            genresText.text = movie.genre
        } else {
            genresText.hide()
        }

        bannerImage.loadImage(movie.mainPosterPath)
        movieTitleText.text = movie.title
        movieYearText.text = movie.releaseDate
    }
}