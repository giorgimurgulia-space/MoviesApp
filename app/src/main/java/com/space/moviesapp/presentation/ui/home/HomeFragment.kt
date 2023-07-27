package com.space.moviesapp.presentation.ui.home


import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.ChipFilterItemBinding
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
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

    override fun setListeners() {
        binding.filterImage.setOnClickListener {
            isFilterVisible()
        }

        binding.cancelSearchText.setOnClickListener {
            binding.filterImage.visibility = View.VISIBLE
            binding.cancelSearchText.visibility = View.GONE
            binding.searchEditText.text.clear()
        }

        binding.searchEditText.doAfterTextChanged {

            binding.filterImage.visibility = View.INVISIBLE
            binding.cancelSearchText.visibility = View.VISIBLE
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            viewModel.onFilterClick(checkedIds)
        }

        binding.mainRecycler.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onBottomScroll()
                }
            }
        })
    }

    private fun setFilter(chips: List<MovieCategoryUIModel>) {
        chips.forEachIndexed { index, it ->
            val chip = ChipFilterItemBinding.inflate(LayoutInflater.from(requireContext())).chipItem
            chip.text = it.title
            chip.id = index

            binding.chipGroup.addView(chip)
        }
        binding.chipGroup.check(0)
    }

    private fun isFilterVisible() {
        if (binding.chipGroup.visibility == View.GONE)
            binding.chipGroup.visibility = View.VISIBLE
        else
            binding.chipGroup.visibility = View.GONE
    }
}