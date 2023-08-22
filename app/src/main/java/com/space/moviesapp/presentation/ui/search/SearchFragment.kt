package com.space.moviesapp.presentation.ui.search

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.FragmentSearchBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.common.adapter.MoviePagingAdapter
import com.space.moviesapp.presentation.common.decorator.GridSpacingItemDecoration
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.ui.search.vm.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {
    override val viewModelClass: KClass<SearchViewModel>
        get() = SearchViewModel::class

    private val adapter = MoviePagingAdapter(
        onItemClicked = { viewModel.navigate(SearchFragmentDirections.actionGlobalDetailsFragment(it)) },
        onFavouriteClick = {
            viewModel.onFavouriteClick(it)
        }
    )

    override fun onBind() {
        val spanCount = 2
        val spacing = 32
        val includeEdge = false

        binding.mainRecycler.adapter = adapter
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
            viewModel.movieSearch(it.toString())
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

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        viewModel.setDialog(DialogItem.LoaderDialog())
                    }
                    is LoadState.Error -> {
                        viewModel.setDialog(DialogItem.ErrorDialog(onRefreshClick = { adapter.refresh() }))
                    }
                    else -> {
                        viewModel.closeLoaderDialog()
                    }
                }
            }
        }
    }
}