package com.space.movie.presentation.ui.search

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.core.extensions.collectFlow
import com.space.movie.databinding.FragmentSearchBinding
import com.space.core.base.fragment.BaseFragment
import com.space.movie.presentation.common.adapter.MoviePagingAdapter
import com.space.core.decorator.GridSpacingItemDecoration
import com.space.core.view.dialog.DialogItem
import com.space.movie.presentation.ui.search.vm.SearchViewModel
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
        collectFlow(viewModel.searchUIState) {
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