package com.space.moviesapp.presentation.ui.home


import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.moviesapp.common.extensions.changeVisibility
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.common.extensions.observeNonNull
import com.space.moviesapp.databinding.ChipFilterItemBinding
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.common.decorator.GridSpacingItemDecoration
import com.space.moviesapp.presentation.common.adapter.MoviePagingAdapter
import com.space.moviesapp.presentation.ui.home.vm.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

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
    }

    override fun setObserves() {
        collectFlow(viewModel.state) {
            adapter.submitData(lifecycle, it)
        }
        collectFlow(viewModel.movieCategory) {
            setFilter(it)
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

    override fun setListeners() = with(binding) {
        filterCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked || chipGroup.visibility == View.VISIBLE)
                chipGroup.changeVisibility()
        }

        searchEditText.setOnClickListener {
            viewModel.navigate(HomeFragmentDirections.actionGlobalSearchFragment())
        }

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            viewModel.onFilterClick(group.checkedChipId)
        }
    }

    private fun setFilter(chips: List<MovieCategoryUIModel>) = with(binding) {
        chipGroup.removeAllViews()
        chips.forEachIndexed { index, it ->
            val chip = ChipFilterItemBinding.inflate(LayoutInflater.from(requireContext())).chipItem

            chip.id = index
            chip.text = it.title
            chip.isChecked = it.isChecked
            chipGroup.addView(chip)
        }
    }
}

