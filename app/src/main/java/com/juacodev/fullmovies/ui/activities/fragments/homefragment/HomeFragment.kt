package com.juacodev.fullmovies.ui.activities.fragments.homefragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.juacodev.fullmovies.databinding.FragmentHomeBinding
import com.juacodev.fullmovies.domain.models.DMovie
import com.juacodev.fullmovies.ui.activities.fragments.BaseFragment
import com.juacodev.fullmovies.ui.activities.fragments.homefragment.components.BasicMoviesAdapter
import com.juacodev.fullmovies.ui.activities.fragments.homefragment.viewmodel.HomeViewModel
import com.juacodev.fullmovies.ui.activities.fragments.listeners.MovieSelected
import com.juacodev.fullmovies.utils.BasicResourceData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
),MovieSelected {
    private val homeViewModel: HomeViewModel by viewModels()
    private val upComingAdapter: BasicMoviesAdapter by lazy {
        BasicMoviesAdapter(this)
    }
    private val trendingAdapter: BasicMoviesAdapter by lazy {
        BasicMoviesAdapter(this)
    }
    override fun onViews() {
        binding.rvSectionUpcoming.adapter=upComingAdapter
        binding.rvSectionTrending.adapter=trendingAdapter
        //homeViewModel.getBasicMovies()

    }

    override fun onViewModels() {
        homeViewModel.toRatedMovies.observe(viewLifecycleOwner){
            when(it){
                is BasicResourceData.Error ->
                    Unit
                is BasicResourceData.Loading -> showLoading(binding.pbHomeviewTrending)
                is BasicResourceData.LocalData -> {
                    hideLoading(binding.pbHomeviewUpcoming)
                    trendingAdapter.setList(it.data?:emptyList())
                }
                is BasicResourceData.Success -> {
                    hideLoading(binding.pbHomeviewTrending)
                    trendingAdapter.setList(it.data?:emptyList())
                }
            }
        }
        homeViewModel.upComingMovies.observe(viewLifecycleOwner){
            when(it){
                is BasicResourceData.Error ->
                    Unit
                is BasicResourceData.Loading -> showLoading(binding.pbHomeviewUpcoming)
                is BasicResourceData.LocalData -> {
                    hideLoading(binding.pbHomeviewUpcoming)
                    upComingAdapter.setList(it.data?:emptyList())
                }
                is BasicResourceData.Success -> {
                    hideLoading(binding.pbHomeviewUpcoming)
                    upComingAdapter.setList(it.data?:emptyList())
                }
            }
        }

    }
    private fun showError(erroView:View){
        erroView.visibility=View.VISIBLE
    }
    private fun hideError(erroView:View){
        erroView.visibility=View.GONE
    }

    private fun showLoading(view:View){
        view.visibility= View.VISIBLE
    }
    private fun hideLoading(view:View){
        view.visibility= View.GONE
    }


    override fun onMovieSelected(movie: DMovie) {
        val action=HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }
}