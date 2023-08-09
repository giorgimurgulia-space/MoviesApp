package com.space.moviesapp.presentation.ui.details.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.space.moviesapp.R
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.common.extensions.fromMinutesToHHmm
import com.space.moviesapp.common.extensions.loadImage
import com.space.moviesapp.common.utils.MoviesConstants.MOVIE_ID
import com.space.moviesapp.databinding.ChipFilterItemBinding
import com.space.moviesapp.databinding.ChipWithIconBinding
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
    }

    private fun setMovieDetailContent(movieModel: MovieDetailsUIModel) = with(binding) {
        movieModel.posterPath?.let { bannerImage.loadImage(it) }
        movieTitleText.text = movieModel.originalTitle
        descriptionTextView.text = movieModel.overview


        //todo empty genres
        if (movieModel.genres.isNotEmpty()) {
            setChips(movieModel.voteAverage.toString(), R.drawable.ic_starr)
            setChips(movieModel.genres.first())
            setChips(movieModel.runtime?.fromMinutesToHHmm()!!, R.drawable.ic_clock)
            setChips(movieModel.releaseDate.toString())
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setChips(title: String, icon: Int? = null) = with(binding) {
        //todo chips design

        val chip = ChipWithIconBinding.inflate(LayoutInflater.from(requireContext())).chipItem
        chip.text = title
        chip.chipIcon = icon?.let { requireContext().getDrawable(it) }
        chipGroup.addView(chip)
    }
}