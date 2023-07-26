package com.space.moviesapp.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MovieUIModel

class MovieDiffUtil : DiffUtil.ItemCallback<MovieUIModel>() {
    override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
        return oldItem == newItem
    }
}