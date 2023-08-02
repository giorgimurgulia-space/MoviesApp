package com.space.moviesapp.presentation.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.space.moviesapp.presentation.model.MoviePageUIModel
import com.space.moviesapp.presentation.model.MovieUIItem

class MovieDiffUtil : DiffUtil.ItemCallback<MovieUIItem>() {
    override fun areItemsTheSame(oldItem: MovieUIItem, newItem: MovieUIItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUIItem, newItem: MovieUIItem): Boolean {
        return oldItem == newItem
    }
}