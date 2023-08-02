package com.space.moviesapp.presentation.ui.home


import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.changeVisibility
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.ChipFilterItemBinding
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.ui.home.adapter.MovieAdapter
import kotlin.reflect.KClass


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val adapter = MovieAdapter()


    override fun onBind() {
        binding.mainRecycler.adapter = adapter
        viewModel.getMovieCategory()
    }

    override fun setObserves() {
        collectFlow(viewModel.movieCategory) {
            setFilter(it)
        }
        collectFlow(viewModel.state) {
            adapter.submitList(it)
        }
    }

    override fun setListeners() = with(binding) {
        filterImage.setOnClickListener {
            chipGroup.changeVisibility()
        }

        cancelSearchText.setOnClickListener {
            binding.filterImage.visibility = View.VISIBLE
            binding.cancelSearchText.visibility = View.GONE
            binding.searchEditText.text.clear()
        }

        searchEditText.doAfterTextChanged {
            filterImage.visibility = View.INVISIBLE
            cancelSearchText.visibility = View.VISIBLE
        }

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            viewModel.onFilterClick(group.checkedChipId)
        }

        mainRecycler.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onBottomScroll()
                }
            }
        })
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