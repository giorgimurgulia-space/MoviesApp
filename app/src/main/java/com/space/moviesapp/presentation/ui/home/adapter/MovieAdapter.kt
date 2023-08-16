package com.space.moviesapp.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.changeVisibility
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieAdapter(
    private val onItemClicked: ((movieId: Int) -> Unit),
    private val onFavouriteClick: ((movie: MovieItemUIModel) -> Unit)
) :
    PagingDataAdapter<MovieItemUIModel, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked,
            onFavouriteClick
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MovieViewHolder(
        private val binding: LayoutMovieItemBinding,
        private val onItemClicked: ((movieId: Int) -> Unit),
        private val onFavouriteClick: ((movie: MovieItemUIModel) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemUIModel) = with(binding) {
            favoriteCheckBox.isChecked = movie.isFavourite

            if (movie.genre.isNotEmpty()) {
                genresText.text = movie.genre
            } else {
                genresText.changeVisibility()
            }

            bannerImage.loadImage(movie.mainPosterPath)
            movieTitleText.text = movie.title
            movieYearText.text = movie.releaseDate

            favoriteCheckBox.setOnClickListener{
                onFavouriteClick(movie)
            }

            binding.root.setOnClickListener {
                onItemClicked(movie.id)
            }
        }
    }
}