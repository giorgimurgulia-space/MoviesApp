package com.space.moviesapp.presentation.ui.favourites.fragment

import com.space.moviesapp.databinding.FragmentFavouritesBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.ui.favourites.vm.FavouritesViewModel
import kotlin.reflect.KClass

class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding, FavouritesViewModel>(FragmentFavouritesBinding::inflate) {
    override val viewModelClass: KClass<FavouritesViewModel>
        get() = FavouritesViewModel::class

    override fun onBind() {
        TODO("Not yet implemented")
    }

    override fun setListeners() {
        super.setListeners()
    }

    override fun setObserves() {
        super.setObserves()
    }
}