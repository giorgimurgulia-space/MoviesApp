package com.space.movie.presentation.ui.home


import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.core.extensions.changeVisibility
import com.space.core.extensions.collectFlow
import com.space.movie.databinding.ChipFilterItemBinding
import com.space.movie.databinding.FragmentHomeBinding
import com.space.core.base.fragment.BaseFragment
import com.space.core.view.dialog.DialogItem
import com.space.movie.presentation.model.MovieCategoryUIModel
import com.space.core.decorator.GridSpacingItemDecoration
import com.space.movie.presentation.common.adapter.MoviePagingAdapter
import com.space.movie.presentation.ui.home.vm.HomeViewModel
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
        collectFlow(viewModel.homeUIState) {
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

