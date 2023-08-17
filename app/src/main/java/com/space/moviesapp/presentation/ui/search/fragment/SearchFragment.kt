package com.space.moviesapp.presentation.ui.search.fragment

import androidx.core.widget.doAfterTextChanged
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.FragmentSearchBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.common.adapter.MoviePagingAdapter
import com.space.moviesapp.presentation.common.decorator.GridSpacingItemDecoration
import com.space.moviesapp.presentation.ui.home.fragment.HomeFragmentDirections
import com.space.moviesapp.presentation.ui.search.vm.SearchViewModel
import kotlin.reflect.KClass

class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {
    override val viewModelClass: KClass<SearchViewModel>
        get() = SearchViewModel::class

    private val adapter = MoviePagingAdapter(
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

        searchListener()
    }

    private fun searchListener() {
        binding.searchEditText.doAfterTextChanged {
            if (!it.isNullOrEmpty() && it.isNotBlank()) {
                viewModel.movieSearch(it.toString())
            }
        }
    }

    override fun setListeners() {
        binding.cancelSearchText.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    override fun setObserves() {
        collectFlow(viewModel.state) {
            adapter.submitData(lifecycle, it)
        }
    }
}