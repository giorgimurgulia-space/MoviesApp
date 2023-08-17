package com.space.moviesapp.presentation.common.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.common.view_holder.MovieViewHolder
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MoviePagingAdapter(
    private val onItemClicked: ((movieId: Int) -> Unit),
    private val onFavouriteClick: ((movie: MovieItemUIModel) -> Unit)
) : PagingDataAdapter<MovieItemUIModel, MovieViewHolder>(MovieDiffUtil()) {

    private val favouriteMoviesIds: MutableMap<Int, Boolean> = mutableMapOf()

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
        getItem(position)?.let { holder.bind(it, favouriteMoviesIds) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onItemFavouriteChange(moviesId: List<Int>) {
        moviesId.forEach {
            favouriteMoviesIds[it] = true
        }
        favouriteMoviesIds.forEach {
            favouriteMoviesIds[it.key] = moviesId.contains(it.key)
        }

        //TODO find position
        notifyDataSetChanged()
    }
}
