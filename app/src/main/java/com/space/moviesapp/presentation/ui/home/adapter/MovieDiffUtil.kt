package com.space.moviesapp.presentation.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MovieDetailsUIModel

class MovieDiffUtil : DiffUtil.ItemCallback<MovieDetailsUIModel>() {
    override fun areItemsTheSame(oldItem: MovieDetailsUIModel, newItem: MovieDetailsUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetailsUIModel, newItem: MovieDetailsUIModel): Boolean {
        return oldItem == newItem
    }
}