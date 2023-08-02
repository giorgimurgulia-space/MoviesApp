package com.space.moviesapp.presentation.ui.home

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.space.moviesapp.R
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    override fun onBind() {
        DialogFragment(R.layout.layout_loader)
            .show(childFragmentManager, "loader")
    }
}