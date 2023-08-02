package com.space.moviesapp.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MovieItemUIModel
import com.space.moviesapp.presentation.model.MovieUIModel

class MovieDiffUtil : DiffUtil.ItemCallback<MovieItemUIModel>() {
    override fun areItemsTheSame(oldItem: MovieItemUIModel, newItem: MovieItemUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItemUIModel, newItem: MovieItemUIModel): Boolean {
        return oldItem == newItem
    }
}