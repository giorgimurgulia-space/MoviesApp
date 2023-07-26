package com.space.moviesapp.presentation.ui.home


import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.chip.Chip
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.ChipFilterItemBinding
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.databinding.LayoutMovieItemBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import kotlin.reflect.KClass

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val adapter = MovieAdapter()


    override fun onBind() {
        setFilter(
            listOf(
                "Popular", "Top Rated"
            )
        )

        binding.mainRecycler.adapter = adapter

        viewModel.getMovies()
    }

    override fun setObserves() {
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
    }

    private fun setFilter(chips: List<String>) {
        chips.forEachIndexed { index, it ->
            val chip = ChipFilterItemBinding.inflate(LayoutInflater.from(requireContext())).chipItem
            chip.text = it
            chip.id = index

            chip.setOnClickListener {
                viewModel.onFilterClick(chip.id)
                toast(chip.id.toString())
            }
            if (index == 0)
                chip.isChecked
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