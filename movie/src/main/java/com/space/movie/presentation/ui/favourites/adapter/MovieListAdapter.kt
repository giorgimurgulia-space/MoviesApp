package com.space.movie.presentation.ui.favourites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.space.movie.databinding.LayoutMovieItemBinding
import com.space.movie.presentation.common.adapter.MovieDiffUtil
import com.space.movie.presentation.common.view_holder.MovieViewHolder
import com.space.movie.presentation.model.MovieItemUIModel

class MovieListAdapter(
    private val onItemClicked: ((movieId: Int) -> Unit),
    private val onFavouriteClick: ((movie: MovieItemUIModel) -> Unit)
) : ListAdapter<MovieItemUIModel, MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding).apply {

            binding.favoriteCheckBox.setOnClickListener {
                onFavouriteClick(getItem(bindingAdapterPosition)!!)
            }

            binding.root.setOnClickListener {
                onItemClicked(getItem(bindingAdapterPosition)!!.id)
            }

        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}