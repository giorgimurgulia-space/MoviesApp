package com.space.moviesapp.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.model.MoviePageUIModel
import com.space.moviesapp.presentation.model.MovieUIItem

class MovieAdapter :
    ListAdapter<MovieUIItem, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(
        private val binding: LayoutMovieItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUIItem) = with(binding) {
            genresText.text = movie.genres.first()

            bannerImage.loadImage(movie.poster)
            movieTitleText.text = movie.title
            movieYearText.text = movie.releaseDate

            favoriteCheckBox.setOnCheckedChangeListener { checkbox, isChecked ->
                // todo > for test button work
                Toast.makeText(binding.root.context, movie.id.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}