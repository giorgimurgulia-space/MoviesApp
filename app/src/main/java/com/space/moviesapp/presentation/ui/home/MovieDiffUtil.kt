package com.space.moviesapp.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MovieUIModel

class MovieDiffUtil : DiffUtil.ItemCallback<MovieUIModel.MovieUIItem>() {
    override fun areItemsTheSame(oldItem: MovieUIModel.MovieUIItem, newItem: MovieUIModel.MovieUIItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUIModel.MovieUIItem, newItem: MovieUIModel.MovieUIItem): Boolean {
        return oldItem == newItem
    }
}