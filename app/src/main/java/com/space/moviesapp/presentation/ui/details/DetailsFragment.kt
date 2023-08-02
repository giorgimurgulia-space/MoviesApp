package com.space.moviesapp.presentation.ui.details

import androidx.fragment.app.viewModels
import com.space.moviesapp.databinding.FragmentDetailsBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(FragmentDetailsBinding::inflate) {

    override val viewModel: DetailsViewModel by viewModels()

    override fun onBind() {
    }
}