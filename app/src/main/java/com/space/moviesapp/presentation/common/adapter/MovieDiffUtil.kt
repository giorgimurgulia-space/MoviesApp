package com.space.moviesapp.presentation.common.adapter

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieDiffUtil : DiffUtil.ItemCallback<MovieItemUIModel>() {
    override fun areItemsTheSame(oldItem: MovieItemUIModel, newItem: MovieItemUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItemUIModel, newItem: MovieItemUIModel): Boolean {
        return oldItem == newItem
    }
}