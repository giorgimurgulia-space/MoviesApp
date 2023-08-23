package com.space.movie.presentation.common.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.space.core.extensions.hide
import com.space.core.extensions.loadImage
import com.space.core.extensions.show
import com.space.movie.databinding.LayoutMovieItemBinding
import com.space.movie.presentation.model.MovieItemUIModel

class MovieViewHolder(
    private val binding: LayoutMovieItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movie: MovieItemUIModel
    ) = with(binding) {

        favoriteCheckBox.isChecked = movie.isFavourite

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