package com.space.moviesapp.presentation.ui.home

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.moviesapp.common.extensions.collectFlow
import com.space.moviesapp.databinding.FragmentHomeBinding
import com.space.moviesapp.presentation.base.fragment.BaseFragment
import kotlin.reflect.KClass

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val adapter = MovieAdapter()


    override fun onBind() {
        viewModel.getMovies()

        binding.mainRecycler.layoutManager =
            GridLayoutManager(requireContext(),2)
        binding.mainRecycler.adapter = adapter
    }

    override fun setObserves() {
        collectFlow(viewModel.state) {
            adapter.submitList(it)
        }
    }
}