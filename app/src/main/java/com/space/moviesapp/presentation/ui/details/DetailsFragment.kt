package com.space.moviesapp.presentation.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.space.moviesapp.R
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.common.extensions.fromMinutesToHHmm
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.common.utils.MoviesConstants.MOVIE_ID
import com.space.moviesapp.databinding.ChipDetailMovieBinding
import com.space.moviesapp.databinding.FragmentDetailsBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import com.space.moviesapp.presentation.ui.details.vm.DetailsViewModel
import kotlin.reflect.KClass

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(FragmentDetailsBinding::inflate) {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    override fun onBind() {
        val movieId = arguments?.getInt(MOVIE_ID)
        viewModel.getMovie(movieId)
    }

    override fun setObserves() {
        collectFlow(viewModel.movieState) {
            setMovieDetailContent(it)
        }
    }

    override fun setListeners() {
        binding.backImage.setOnClickListener {
            viewModel.navigateBack()
        }
        binding.favoriteCheckBox.setOnClickListener {
            viewModel.onFavouriteClick()
        }
    }

    private fun setMovieDetailContent(movieModel: MovieDetailsUIModel) = with(binding) {
        bannerImage.loadImage(movieModel.backdropPoster)
        movieTitleText.text = movieModel.title
        favoriteCheckBox.isChecked = movieModel.isFavourite
        descriptionTextView.text = movieModel.overview

        chipGroup.removeAllViews()
        setChip(movieModel.voteAverage.toString(), R.drawable.ic_starr)
        setChip(movieModel.genre)
        setChip(movieModel.runtime.fromMinutesToHHmm(), R.drawable.ic_clock)
        setChip(movieModel.releaseDate)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setChip(title: String?, icon: Int? = null) = with(binding) {
        if (!title.isNullOrEmpty()) {
            val chip =
                ChipDetailMovieBinding.inflate(LayoutInflater.from(requireContext())).chipItem
            chip.text = title
            chip.chipIcon = icon?.let { requireContext().getDrawable(it) }
            chipGroup.addView(chip)
        }
    }
}