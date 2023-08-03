package com.space.moviesapp.presentation.ui.details.fragment

import com.space.moviesapp.common.utils.MoviesConstants.MOVIE_ID
import com.space.moviesapp.databinding.FragmentDetailsBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.ui.details.vm.DetailsViewModel
import kotlin.reflect.KClass

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(FragmentDetailsBinding::inflate) {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    override fun onBind() {
        val movieId = arguments?.getString(MOVIE_ID)
    }
}