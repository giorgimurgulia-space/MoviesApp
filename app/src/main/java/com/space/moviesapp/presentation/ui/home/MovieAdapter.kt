package com.space.moviesapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.model.MovieUIModel

class MovieAdapter : ListAdapter<MovieUIModel, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(
        private val binding: LayoutMovieItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUIModel) = with(binding) {
            categoryLayout.categoryText.text = "Comedy"
            bannerImage.loadImage(movie.poster)
            movieTitleText.text = movie.title
            movieYearText.text = movie.releaseDate
        }
    }
}