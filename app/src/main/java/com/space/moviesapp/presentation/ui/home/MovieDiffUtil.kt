package com.space.moviesapp.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MovieUIModel

class MovieDiffUtil : DiffUtil.ItemCallback<MovieUIModel.MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieUIModel.MovieItem, newItem: MovieUIModel.MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUIModel.MovieItem, newItem: MovieUIModel.MovieItem): Boolean {
        return oldItem == newItem
    }
}