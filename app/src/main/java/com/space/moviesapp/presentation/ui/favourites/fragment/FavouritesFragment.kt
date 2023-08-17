package com.space.moviesapp.presentation.ui.favourites.fragment

import android.view.View
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.FragmentFavouritesBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.ui.favourites.adapter.MovieListAdapter
import com.space.moviesapp.presentation.common.decorator.GridSpacingItemDecoration
import com.space.moviesapp.presentation.ui.favourites.vm.FavouritesViewModel
import com.space.moviesapp.presentation.ui.home.fragment.HomeFragmentDirections
import kotlin.reflect.KClass

class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding, FavouritesViewModel>(FragmentFavouritesBinding::inflate) {
    override val viewModelClass: KClass<FavouritesViewModel>
        get() = FavouritesViewModel::class

    private val adapter = MovieListAdapter(
        onItemClicked = { viewModel.navigate(HomeFragmentDirections.actionGlobalDetailsFragment(it)) },
        onFavouriteClick = {
            viewModel.onFavouriteClick(it)
        }
    )

    override fun onBind() {
        binding.mainRecycler.adapter = adapter

        val spanCount = 2
        val spacing = 32
        val includeEdge = false
        binding.mainRecycler.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount,
                spacing,
                includeEdge
            )
        )
    }

    override fun setListeners() {
        super.setListeners()
    }

    override fun setObserves() {
        collectFlow(viewModel.state) {
            binding.noMoviesImage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE

            adapter.submitList(it)
        }
    }
}