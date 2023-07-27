package com.space.moviesapp.presentation.ui.favorites

import com.space.moviesapp.databinding.FragmentFavoritesBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import kotlin.reflect.KClass

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(FragmentFavoritesBinding::inflate) {
    override val viewModelClass: KClass<FavoritesViewModel>
        get() = FavoritesViewModel::class

    override fun onBind() {

    }
}