package com.space.moviesapp.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.changeVisibility
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.model.MovieItemUIModel

class MovieAdapter(private val onItemClicked: ((movieId: Int) -> Unit)) :
    PagingDataAdapter<MovieItemUIModel, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            onItemClicked(it)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MovieViewHolder(
        private val binding: LayoutMovieItemBinding,
        private val onItemClicked: ((movieId: Int) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemUIModel) = with(binding) {

            if (movie.genres.isNotEmpty()) {
                genresText.text = movie.genres.first().toString()
            } else {
                genresText.changeVisibility()
            }

            bannerImage.loadImage(movie.poster)
            movieTitleText.text = movie.title
            movieYearText.text = movie.releaseDate

            favoriteCheckBox.setOnCheckedChangeListener { checkbox, isChecked ->
                // todo / for test button work
                Toast.makeText(binding.root.context, movie.id.toString(), Toast.LENGTH_SHORT).show()
            }

            binding.root.setOnClickListener {
                onItemClicked(movie.id)
            }
        }
    }
}