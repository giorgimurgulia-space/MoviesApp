package com.space.moviesapp.presentation.ui.favorites

import androidx.fragment.app.viewModels
import com.space.moviesapp.databinding.FragmentFavoritesBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(FragmentFavoritesBinding::inflate) {
    override val viewModel: FavoritesViewModel by viewModels()

    override fun onBind() {

    }
}