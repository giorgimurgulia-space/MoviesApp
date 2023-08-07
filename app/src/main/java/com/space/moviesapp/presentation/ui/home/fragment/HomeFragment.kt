package com.space.moviesapp.presentation.ui.home.fragment


import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.moviesapp.common.extensions.changeVisibility
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.common.extensions.observeNonNull
import com.space.moviesapp.databinding.ChipFilterItemBinding
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.dialog.ErrorDialogFragment
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.ui.home.adapter.GridSpacingItemDecoration
import com.space.moviesapp.presentation.ui.home.adapter.MovieAdapter
import com.space.moviesapp.presentation.ui.home.vm.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val adapter = MovieAdapter()


    override fun onBind() {
        viewModel.getMovieCategory()

        binding.mainRecycler.adapter = adapter

        binding.mainRecycler.addItemDecoration(
            GridSpacingItemDecoration(2, 32, false)
        )
    }

    override fun setObserves() {
        collectFlow(viewModel.state) {
            adapter.submitData(lifecycle, it)
        }

        collectFlow(viewModel.movieCategory) {
            setFilter(it)
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        viewModel.setDialog(DialogItem.LoaderDialog())
                    }
                    is LoadState.Error -> {
                        viewModel.setDialog(DialogItem.ErrorDialog(onRefreshClick = { viewModel.refresh() }))
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

        cancelSearchText.setOnClickListener {
            searchEditText.clearFocus()
            closeKeyBoard()

            chipGroup.check(0)
        }

        searchEditText.setOnFocusChangeListener { view, b ->
            searchEditText.text.clear()
            cancelSearchText.changeVisibility()
            filterCheckBox.isChecked = false
            filterCheckBox.changeVisibility()
            chipGroup.clearCheck()
        }

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            viewModel.onFilterClick(group.checkedChipId)
        }
    }

    private fun setFilter(chips: List<MovieCategoryUIModel>) = with(binding) {
        chips.forEachIndexed { index, it ->
            val chip = ChipFilterItemBinding.inflate(LayoutInflater.from(requireContext())).chipItem
            chip.text = it.title
            chip.id = index

            chipGroup.addView(chip)
        }
        chipGroup.check(0)
    }
}
