package com.space.moviesapp.presentation.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.model.MovieUIModel

class MovieAdapter : PagerAdapter<MovieUIModel, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    class MovieViewHolder(
        private val binding: LayoutMovieItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: MovieUIModel) = with(binding) {

        }
    }
}