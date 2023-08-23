package com.space.moviesapp.presentation.ui.favourites

import android.view.View
import com.space.core.extensions.collectFlow
import com.space.moviesapp.databinding.FragmentFavouritesBinding
import com.space.core.base.fragment.BaseFragment
import com.space.moviesapp.presentation.ui.favourites.adapter.MovieListAdapter
import com.space.core.decorator.GridSpacingItemDecoration
import com.space.moviesapp.presentation.ui.favourites.vm.FavouritesViewModel
import kotlin.reflect.KClass

class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding, FavouritesViewModel>(FragmentFavouritesBinding::inflate) {
    override val viewModelClass: KClass<FavouritesViewModel>
        get() = FavouritesViewModel::class

    private val adapter = MovieListAdapter(
        onItemClicked = { viewModel.navigate(FavouritesFragmentDirections.actionGlobalDetailsFragment(it)) },
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

    override fun setObserves() {
        collectFlow(viewModel.favouriteUIState) {
            binding.noMoviesImage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(it)
        }
    }
}